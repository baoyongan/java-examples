package com.baoyongan.java.eg.swing.panels;

import com.baoyongan.java.eg.swing.modles.CourseRule;
import com.baoyongan.java.eg.swing.modles.DayCourseTimeItem;
import com.baoyongan.java.eg.swing.modles.WeekItem;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * 西测的容器
 * Created by bqct_bya on 2017/5/24.
 */
public class WestPanel extends JPanel{

    java.util.List<CourseRule> rules=new ArrayList<CourseRule>();
    private GridBagConstraints gridBagConstraints=new GridBagConstraints();
    private Object object=new Object();

    public WestPanel() {
        init();
    }

    private void init(){

        setLayout(new GridBagLayout()); // 网格布局

        addRule();

    }

    /**
     * 添加rule
     */
    public void addRule() {
        synchronized (object){

            int i=rules.size()*3;
            gridBagConstraints.gridy=i;
            gridBagConstraints.gridx=0;
           // gridBagConstraints.gridwidth=2;
            JLabel label=new JLabel("------------规则"+(rules.size()+1)+"-----------");
            add(label,gridBagConstraints);

            gridBagConstraints.gridy=i+1;
            gridBagConstraints.gridx=0;
            JLabel label0=new JLabel("选择星期：");
            add(label0,gridBagConstraints);

            JComboBox<WeekItem> weekBox=new JComboBox<WeekItem>();
            weekBox.addItem(new WeekItem(1,"星期一"));
            weekBox.addItem(new WeekItem(2,"星期二"));
            weekBox.addItem(new WeekItem(3,"星期三"));
            weekBox.addItem(new WeekItem(4,"星期四"));
            weekBox.addItem(new WeekItem(5,"星期五"));
            weekBox.addItem(new WeekItem(6,"星期六"));
            weekBox.addItem(new WeekItem(7,"星期日"));
            gridBagConstraints.gridy=i+1;
            gridBagConstraints.gridx=1;
            add(weekBox,gridBagConstraints);

            gridBagConstraints.gridy=i+2;
            gridBagConstraints.gridx=0;
            JLabel label1=new JLabel("选择该日学时量：");
            add(label1,gridBagConstraints);

            JComboBox<DayCourseTimeItem> dayBox=new JComboBox<DayCourseTimeItem>();
            dayBox.addItem(new DayCourseTimeItem(1,"1个课时"));
            dayBox.addItem(new DayCourseTimeItem(2,"2个课时"));
            dayBox.addItem(new DayCourseTimeItem(3,"3个课时"));
            dayBox.addItem(new DayCourseTimeItem(4,"4个课时"));
            dayBox.addItem(new DayCourseTimeItem(5,"5个课时"));
            dayBox.addItem(new DayCourseTimeItem(6,"6个课时"));
            dayBox.addItem(new DayCourseTimeItem(7,"7个课时"));
            dayBox.addItem(new DayCourseTimeItem(8,"8个课时"));
            dayBox.addItem(new DayCourseTimeItem(9,"9个课时"));
            dayBox.addItem(new DayCourseTimeItem(10,"10个课时"));
            dayBox.addItem(new DayCourseTimeItem(11,"11个课时"));
            dayBox.addItem(new DayCourseTimeItem(12,"12个课时"));
            gridBagConstraints.gridy=i+2;
            gridBagConstraints.gridx=1;
            add(dayBox,gridBagConstraints);
            rules.add(new CourseRule(weekBox,dayBox));
        }
    }

    public java.util.List<CourseRule> getRules() {
        return rules;
    }
}
