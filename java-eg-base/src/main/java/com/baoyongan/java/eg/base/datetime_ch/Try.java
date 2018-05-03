package com.baoyongan.java.eg.base.datetime_ch;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Try {
    public static void main(String[] args) {
        LocalDate date=LocalDate.now();

        //
        ZoneId defaultZoneId=ZoneId.systemDefault();
        System.out.println("defaultZoneId:"+defaultZoneId);

        //
        Clock defaultClock= Clock.systemDefaultZone();
        System.out.println("defaultClock"+defaultClock);

        //
        SimpleDateFormat sdf=new SimpleDateFormat("z");
        System.out.println("sdf:"+sdf.format(new Date()));

        //


    }
}
