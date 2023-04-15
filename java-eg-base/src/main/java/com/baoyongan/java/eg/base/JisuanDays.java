package com.baoyongan.java.eg.base;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class JisuanDays {

    public static void main(String[] args) {
        // 年度日历配置
        TreeMap<String, Integer> dayConfig = new TreeMap<>();
        dayConfig.put("2022-09-01", 1);
        dayConfig.put("2022-09-02", 2);
        dayConfig.put("2022-09-03", 2);
        dayConfig.put("2022-09-04", 1);
        dayConfig.put("2022-09-05", 3);
        /** ..省略. **/

        // 工作时间配置
        WorkTimeConfig workTimeConfig = new WorkTimeConfig("08:30:00", "12:30:00", "13:30:00", "17:30:00");
        // 计算两个时间的工作日
        double dy = calcWorkDay(dayConfig, workTimeConfig, "2022-09-01 13:30:00", "2022-09-05 17:30:00");
        System.out.println(dy);


    }

    private static double calcWorkDay(TreeMap<String, Integer> dayConfig, WorkTimeConfig workTimeConfig, String start, String end) {
        String startDay = start.substring(0, 10);
        String endDay = end.substring(0, 10);
        String startTime = start.substring(11, 19);
        String endTime = end.substring(11, 19);
        // 一天
        if (startDay.equals(endDay)) {
            Integer type = dayConfig.get(startDay);
            if (1 == type.intValue()) {
                // 工作日
                return getDayWordTime(startTime, endTime, workTimeConfig);
            }
        }
        // 跨天
        double sum = 0.0;
        SortedMap<String, Integer> sortedMap = dayConfig.subMap(startDay, endDay);
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            if (1 == entry.getValue().intValue()) {
                String day = entry.getKey();
                if (day.equals(startDay))
                    sum += getDayWordTime(startTime, workTimeConfig.getXx(), workTimeConfig);
                else if (day.equals(endDay)) {
                    sum += getDayWordTime(workTimeConfig.getSs(), endTime, workTimeConfig);
                } else {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    /**
     * 计算当天工作时间
     *
     * @param startTime
     * @param endTime
     * @param workTimeConfig
     * @return
     */
    private static double getDayWordTime(String startTime, String endTime, WorkTimeConfig workTimeConfig) {
        // 不到半天 算半天
        if (startTime.compareTo(workTimeConfig.sx) > 0 || endTime.compareTo(workTimeConfig.xs) < 0)
            return 0.5;
        // 其他算一天
        return 1.0;
    }

    /**
     * 工作日时间配置
     */
    static class WorkTimeConfig {
        private String ss;
        private String sx;
        private String xs;
        private String xx;

        public WorkTimeConfig(String ss, String sx, String xs, String xx) {
            this.ss = ss;
            this.sx = sx;
            this.xs = xs;
            this.xx = xx;
        }

        public String getSs() {
            return ss;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }

        public String getSx() {
            return sx;
        }

        public void setSx(String sx) {
            this.sx = sx;
        }

        public String getXs() {
            return xs;
        }

        public void setXs(String xs) {
            this.xs = xs;
        }

        public String getXx() {
            return xx;
        }

        public void setXx(String xx) {
            this.xx = xx;
        }
    }
}
