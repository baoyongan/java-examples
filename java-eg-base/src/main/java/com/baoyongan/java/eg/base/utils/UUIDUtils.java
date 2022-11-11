package com.baoyongan.java.eg.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtils {

    public static void main(String[] args) throws ParseException {
        UUID.randomUUID();

        String timeStr="2017-05-15 14:02:06";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time=sdf.parse(timeStr);
        SimpleDateFormat sdf1=new SimpleDateFormat("【yyyy年M月d日H时m分】");
        System.out.println(sdf1.format(time));
    }
}
