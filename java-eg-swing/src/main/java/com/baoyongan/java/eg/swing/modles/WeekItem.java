package com.baoyongan.java.eg.swing.modles;

/**
 * Created by bqct_bya on 2017/5/24.
 */
public class WeekItem {
    private int week;
    private String weekStr;

    public WeekItem(int week, String weekStr) {
        this.week = week;
        this.weekStr = weekStr;
    }

    public int getWeek() {
        return week;
    }

    public String getWeekStr() {
        return weekStr;
    }

    @Override
    public String toString() {
        return this.weekStr;
    }
}
