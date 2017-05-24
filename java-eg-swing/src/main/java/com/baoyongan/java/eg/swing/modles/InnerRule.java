package com.baoyongan.java.eg.swing.modles;

/**
 * Created by bqct_bya on 2017/5/24.
 */
public class InnerRule {
    private int week;
    private int courseTimes;

    public InnerRule(int week, int courseTimes) {
        this.week = week;
        this.courseTimes = courseTimes;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(int courseTimes) {
        this.courseTimes = courseTimes;
    }
}
