package com.baoyongan.java.eg.swing.frames;

import com.baoyongan.java.eg.swing.modles.CourseRule;
import com.baoyongan.java.eg.swing.modles.DayCourseTimeItem;
import com.baoyongan.java.eg.swing.modles.InnerRule;
import com.baoyongan.java.eg.swing.modles.WeekItem;
import com.baoyongan.java.eg.swing.panels.NorthPanel;
import com.baoyongan.java.eg.swing.panels.SouthPanel;
import com.baoyongan.java.eg.swing.panels.TableTempData;
import com.baoyongan.java.eg.swing.panels.WestPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.RuleBasedCollator;
import java.util.*;
import java.util.List;

/**
 * 课程预计算窗口
 * Created by bqct_bya on 2017/5/24.
 */
public class PreCourseFrame extends JFrame{

    private String title="课时预览";
    private String fileicon="/icons/fisrt.jpg";
    private  NorthPanel northPanel;
    private  SouthPanel southPanel;
    private  WestPanel westPanel;
    private JScrollPane centerPanel;
    private TableTempData tableData;


    /**
     * 添加窗口在屏幕中间
     * @param screenHeight
     * @param screenWidth
     */
    public PreCourseFrame(int screenHeight, int screenWidth) {
        setSize(screenWidth/2,screenHeight/2);
        setLocation(screenWidth/4,screenHeight/4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        ImageIcon icon=new ImageIcon(getClass().getResource(fileicon));
        setIconImage(icon.getImage());

        tableData=new TableTempData();
        // 创建北面面板
        this.northPanel=new NorthPanel();
        this.southPanel=new SouthPanel(this);
        this.westPanel=new WestPanel();
        this.centerPanel=new JScrollPane(tableData.getTable());

        // 添加面板
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel,BorderLayout.WEST);
        add(centerPanel,BorderLayout.CENTER);

        setVisible(Boolean.TRUE);
    }

    /**
     * 生成课时表
     */
    public void doCrouse() {
        System.out.println("开始生成课时表");
        String startDate=northPanel.getStartDateField().getText();
        System.out.println("startDate----->"+startDate);
        if(null==startDate||"".equals(startDate)){
            JOptionPane.showMessageDialog(this,"开始日期不能为空");
            return;
        }

        String endDate=northPanel.getEndDateField().getText();
        System.out.println("endDate----->"+endDate);

        String totalCourseCount=northPanel.getTotalCourseCountField().getText();
        System.out.println("totalCourseCount----->"+totalCourseCount);

        if((null==endDate||"".equals(endDate))&&(null==totalCourseCount||"".equals(totalCourseCount))){
            JOptionPane.showMessageDialog(this,"结束日期和总课时至少输入一项");
            return;
        }

        java.util.List<CourseRule> rules=westPanel.getRules();
        Map<Integer,InnerRule> ruleMap=new HashMap<Integer,InnerRule>();
        for (int i = 0; i < rules.size(); i++) {
            CourseRule rule=rules.get(i);
            WeekItem weekItem= (WeekItem) rule.getWeek().getSelectedItem();
            DayCourseTimeItem dayItem= (DayCourseTimeItem) rule.getCourseCount().getSelectedItem();
            System.out.println("规则"+i+"--"+weekItem+"--"+dayItem);
            if(ruleMap.containsKey(weekItem.getWeek())){
                JOptionPane.showMessageDialog(this,"有重复的规则，请重新选择");
                return;
            }
            ruleMap.put(weekItem.getWeek(),new InnerRule(weekItem.getWeek(),dayItem.getCourseTimeCount()));
        }

        List<Map> list=getPreData(startDate,endDate,totalCourseCount,ruleMap);
        if(list!=null&& list.size()>0){
            tableData.flushData(list);
        }

    }


    private List<Map> getPreData(String startDate, String endDate, String totalCourseCount, Map<Integer, InnerRule> ruleMap) {
        List<Map> result=new ArrayList<Map>();
        for (int i = 0; i <19 ; i++) {
            Map temp=new HashMap();
            temp.put("course",i+1+"");
            temp.put("date","2017-08-23");
            result.add(temp);
        }
        return result;
    }

    public NorthPanel getNorthPanel() {
        return northPanel;
    }

    public SouthPanel getSouthPanel() {
        return southPanel;
    }

    public WestPanel getWestPanel() {
        return westPanel;
    }
}
