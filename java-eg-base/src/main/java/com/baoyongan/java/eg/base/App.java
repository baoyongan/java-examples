package com.baoyongan.java.eg.base;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws ParseException {
        System.out.println( "Hello World!" );

//        BigDecimal a=new BigDecimal("0.99");
//        BigDecimal b=new BigDecimal("0.99");
//        System.out.println(a.multiply(b).setScale(4,BigDecimal.ROUND_HALF_UP));
//
//        BigDecimal a1=new BigDecimal("70.5");
//        BigDecimal t=new BigDecimal("100.50");
//        System.out.println(a1.divide(new BigDecimal("100.00")).multiply(t).setScale(2,BigDecimal.ROUND_HALF_UP));
//
//        double qq=123.2323;
//        double bv=11.2;
//        BigDecimal ddd=BigDecimal.valueOf(qq).divide(BigDecimal.valueOf(bv));
//        System.out.println(ddd.toPlainString());
//        BigDecimal d=ddd.setScale(2,BigDecimal.ROUND_HALF_UP);
//        System.out.println(d);

       /* BigDecimal c=a.add(b);
        a=a.add(b);
        a=a.add(c);
        System.out.println(c);
        System.out.println(a);*/

        String srcFilePath="D:\\servers\\apache-tomcat-7.0.92-for-config\\webapps\\photobase\\photos\\jmapply\\2020-07-06\\B200706002330\\043a79a761ff4ea6976e5bb62dbe9990_.jpg";

        System.out.println(srcFilePath.substring(srcFilePath.lastIndexOf(".")));
        System.out.println(srcFilePath.substring(0,srcFilePath.lastIndexOf(".")));
    }
}
