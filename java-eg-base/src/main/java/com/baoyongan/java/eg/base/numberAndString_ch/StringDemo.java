package com.baoyongan.java.eg.base.numberAndString_ch;

public class StringDemo {
    public static void main(String[] args) {


        System.err.println("【交警在线】请查收《道路交通事故认定书（简易程序）》(打印地址: http://t.cn/AiE68RTl )。可下载交警在线查看案件详情，并及时保险报案。".length());
        System.err.println("【交警在线】请查收《道路交通事故自行协商协议书》(打印地址: http://47.111.177.170/photobase/photos/signprocesspage//2019-09-10/1310201909101548159740001/97fff998-1ace-44cd-a33c-06fb868270a9picture.html )。可下载交警在线查看案件详情，并及时保险报案。".length());


        String palindrome = "Dot saw I was Tod";
        int len = palindrome.length();
        char[] tempCharArray = new char[len];
        char[] charArray = new char[len];

        // put original string in an
        // array of chars
        for (int i = 0; i < len; i++) {
            tempCharArray[i] =
                    palindrome.charAt(i);
        }

        // reverse array of chars
        for (int j = 0; j < len; j++) {
            charArray[j] =
                    tempCharArray[len - 1 - j];
        }

        String reversePalindrome =
                new String(charArray);
        System.out.println(reversePalindrome);

        StringBuilder sb;

        StringBuffer sbb;
    }
}
