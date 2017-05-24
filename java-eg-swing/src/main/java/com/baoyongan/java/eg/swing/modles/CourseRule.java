package com.baoyongan.java.eg.swing.modles;

import javax.swing.*;

/**
 * Created by bqct_bya on 2017/5/24.
 */
public class CourseRule {

    private JComboBox week;
    private JComboBox courseCount;

    public CourseRule(JComboBox week, JComboBox courseCount) {
        this.week = week;
        this.courseCount = courseCount;
    }

    public JComboBox getWeek() {
        return week;
    }

    public JComboBox getCourseCount() {
        return courseCount;
    }

    public void setWeek(JComboBox week) {
        this.week = week;
    }

    public void setCourseCount(JComboBox courseCount) {
        this.courseCount = courseCount;
    }
}
