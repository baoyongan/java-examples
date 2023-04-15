package com.baoyongan.java.eg.base.collection_ch;

import java.util.*;
import java.util.stream.Collectors;

public class Q1_Stream {

    private List<QingjiaRrcord> records;

    public Q1_Stream(List<QingjiaRrcord> records) {
        this.records = records;
    }

    /**
     * 按部门、年月、人员分组汇总个人总请假天数
     * @return
     */
    public Map<String, Map<String, Map<String, List<QingjiaRrcord>>>> groupDayByBumenByDayByRenyuan1(){
      return records.stream().collect(Collectors.groupingBy(QingjiaRrcord::getDeptName,Collectors.groupingBy(reocrd -> reocrd.getYear() + reocrd.getMonth(),Collectors.groupingBy(QingjiaRrcord::getPsnName))));
    }

    /**
     * 按部门、年月、人员分组汇总个人总请假天数
     * @return
     */
    public void groupDayByBumenByDayByRenyuan(){
        Map<String, Map<String, Map<String, IntSummaryStatistics>>> groupsMap = records.stream().collect(Collectors.groupingBy(QingjiaRrcord::getDeptName, Collectors.groupingBy(reocrd -> reocrd.getYear() + reocrd.getMonth(), Collectors.groupingBy(QingjiaRrcord::getPsnName, Collectors.summarizingInt(QingjiaRrcord::getAmount)))));
        groupsMap.forEach((k,v)->{
            v.forEach((kk,vv)->{
                vv.forEach((kkk,vvv)->{
                    System.out.printf("部门： %s，年月：%s, 员工：%s, 请假天数：%d\r\n",k,kk,kkk,vvv.getSum());
                });
            });
        });
    }


    public static void main(String[] args) {
        List<QingjiaRrcord> records=new ArrayList<>();
        records.add(new QingjiaRrcord("张三",1,"1部门",1,"2022","1","A",2));
        records.add(new QingjiaRrcord("张三",1,"1部门",1,"2022","2","A",2));
        records.add(new QingjiaRrcord("张三",1,"1部门",1,"2022","1","B",2));
        records.add(new QingjiaRrcord("李四",1,"1部门",2,"2022","1","A",2));
        records.add(new QingjiaRrcord("王五",2,"2部门",3,"2022","1","A",2));
        Q1_Stream q1_stream = new Q1_Stream(records);
        // 1、按部门、年月、人员分组汇总个人总请假天数
        q1_stream.groupDayByBumenByDayByRenyuan();
        // 2、汇总后的数据按部门序号及人员排序进行排列
        q1_stream.sortGroupDayByBumenByDayByRenyuan(q1_stream.groupDayByBumenByDayByRenyuan1());
        // 3、点击某人，某月数据，可按请假类型汇总显示总请假天数
        q1_stream.getYuanGongAndYearMountDaysGroupByType("张三","2022","1");

    }

    private void sortGroupDayByBumenByDayByRenyuan(Map<String, Map<String, Map<String, List<QingjiaRrcord>>>> groupsMap) {
        // 分组后的结果转list
        List<Map<String,Object>> result=new ArrayList<>();
        groupsMap.forEach((k,v)->{
            v.forEach((kk,vv)->{
                result.addAll(vv.entrySet().stream().map(e->{
                    List<QingjiaRrcord> value = e.getValue();
                    long sum = value.stream().collect(Collectors.summarizingInt(QingjiaRrcord::getAmount)).getSum();
                    QingjiaRrcord qingjiaRrcord = value.get(0);
                    HashMap<String, Object> temp = new HashMap<>();
                    temp.put("部门",qingjiaRrcord.getDeptName());
                    temp.put("部门排序",qingjiaRrcord.getDeptSort());
                    temp.put("年月",kk);
                    temp.put("员工",qingjiaRrcord.getPsnName());
                    temp.put("员工排序",qingjiaRrcord.getPsnSort());
                    temp.put("请假天数",sum);
                    return temp;
                }).collect(Collectors.toList()));
            });
        });
        // 排序前打印
        System.out.println(Arrays.toString(result.toArray()));
        // 对结果排序
        List<Map<String, Object>> collectSorted = result.stream().sorted((m1, m2) -> {
            Integer b1 = (Integer) m1.get("部门排序");
            Integer b2 = (Integer) m2.get("部门排序");
            Integer y1 = (Integer) m1.get("员工排序");
            Integer y2 = (Integer) m2.get("员工排序");
            if (b1.intValue() == b2.intValue()) {
                return y1.intValue() - y2.intValue();
            } else {
                return b1.intValue() - b2.intValue();
            }
        }).collect(Collectors.toList());
        // 排序后打印
        System.out.println(Arrays.toString(collectSorted.toArray()));
    }

    /**
     * 某人，某月数据，可按请假类型汇总显示总请假天数
     * @param name
     * @param year
     * @param mont
     */
    private void getYuanGongAndYearMountDaysGroupByType(String name, String year, String mont) {
        Map<String, List<QingjiaRrcord>> collect = records.stream().filter((record) -> {
            return record.getPsnName().equals(name)
                    && record.getYear().equals(year)
                    && record.getMonth().equals(mont);
        }).collect(Collectors.groupingBy(QingjiaRrcord::getType));
        collect.forEach((v,k)->{
            System.out.printf("员工：%s，年月：%s, %s类型请假天数：%d\r\n",name,year+mont,v,k.stream().collect(Collectors.summarizingInt(QingjiaRrcord::getAmount)).getSum());
        });
    }


    public static class QingjiaRrcord{

        private String psnName;
        private int deptSort;
        private String deptName;
        private int psnSort;
        private String year;
        private String month;
        private String type;
        private int amount;

        public QingjiaRrcord(String psnName, int deptSort, String deptName, int psnSort, String year, String month, String type, int amount) {
            this.psnName = psnName;
            this.deptSort = deptSort;
            this.deptName = deptName;
            this.psnSort = psnSort;
            this.year = year;
            this.month = month;
            this.type = type;
            this.amount = amount;
        }

        public String getPsnName() {
            return psnName;
        }

        public void setPsnName(String psnName) {
            this.psnName = psnName;
        }

        public int getDeptSort() {
            return deptSort;
        }

        public void setDeptSort(int deptSort) {
            this.deptSort = deptSort;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public int getPsnSort() {
            return psnSort;
        }

        public void setPsnSort(int psnSort) {
            this.psnSort = psnSort;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

}
