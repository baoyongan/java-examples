package com.baoyongan.java.eg.swing.modles;

/**
 * Created by bqct_bya on 2017/5/24.
 */
public class DayCourseTimeItem {
    private int courseTimeCount;
    private String courseTimeCountStr;

    public DayCourseTimeItem(int courseTimeCount, String courseTimeCountStr) {
        this.courseTimeCount = courseTimeCount;
        this.courseTimeCountStr = courseTimeCountStr;
    }

    public int getCourseTimeCount() {
        return courseTimeCount;
    }

    public String getCourseTimeCountStr() {
        return courseTimeCountStr;
    }

    @Override
    public String toString() {
        return this.courseTimeCountStr;
    }
}
