package com.baoyongan.java.eg.swing.panels;

import javax.swing.*;
import java.awt.*;

/**
 * 北测的容器
 * Created by bqct_bya on 2017/5/24.
 */
public class NorthPanel extends JPanel{
    private JTextField startDateField;
    private JLabel startDateLabel=new JLabel("课程开始日期：");
    private JTextField endDateField;
    private JLabel endDateLabel=new JLabel("课程结束日期：");
    private JTextField totalCourseCountField;
    private JLabel totalCourseCountLabel=new JLabel("课程总课时数量：");

    public NorthPanel() {
        init();
    }

    private void init(){
        setLayout(new FlowLayout());
        this.startDateField= new JTextField(10);
        startDateField.setToolTipText("请输入日期：格式yyyy-MM-dd");
        add(startDateLabel);
        add(startDateField);
        this.endDateField= new JTextField(10);
        endDateLabel.setToolTipText("请输入日期：格式yyyy-MM-dd");
        add(endDateLabel);
        add(endDateField);
        this.totalCourseCountField= new JTextField(5);
        totalCourseCountLabel.setToolTipText("课程总课时数量");
        add(totalCourseCountLabel);
        add(totalCourseCountField);
    }

    public JTextField getStartDateField() {
        return startDateField;
    }

    public JTextField getEndDateField() {
        return endDateField;
    }

    public JTextField getTotalCourseCountField() {
        return totalCourseCountField;
    }
}
