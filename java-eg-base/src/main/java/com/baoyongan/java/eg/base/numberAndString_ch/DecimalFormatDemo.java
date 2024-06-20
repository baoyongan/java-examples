package com.baoyongan.java.eg.base.numberAndString_ch;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class DecimalFormatDemo {
    static public void customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        System.out.println(value + "  " + pattern + "  " + output);
    }

    static public void main(String[] args) {

//        System.out.println(changeToBig("3000000.0", "s"));

        double s=0;
        System.out.println(s);
        System.out.println(double2BigDecimal(s));
        System.out.println(double2BigDecimal(s).toPlainString());

        customFormat("###,###.###", 123456.789);
        customFormat("###.##", 123456.789);
        customFormat("000000.000", 123.78);
        customFormat("$###,###.###", 12345.67);


        System.out.println(changeToBig("999999999.11",null));
    }


    public static BigDecimal double2BigDecimal (double d){
        return  new BigDecimal(Double.toString(d));
    }

    public static String changeToBig(String stringvalue, String type) {
        String confix = "[0-9]*(\\.?)[0-9]*";
        String zero1 = "0.00";
        String zero2 = "00";
        String sStr = "s";
        if (StringUtils.isBlank(stringvalue)) {
            stringvalue = "0";
        }
        stringvalue = stringvalue.replace("，", "").replace(",", "");
        Pattern pattern = Pattern.compile(confix);
        if (!pattern.matcher(stringvalue).matches()) {
            return "";
        }
        BigDecimal bigd = new BigDecimal(stringvalue);
        String ft = "#############0.00";
        DecimalFormat df = new DecimalFormat(ft);
        stringvalue = df.format(bigd.doubleValue()).toString();
        if (zero1.equals(stringvalue)) {
            return sStr.equals(type) ? "零份" : "零元整";
        }
        char[] hunit = {'拾', '佰', '仟'};// 段内位置表示
        char[] vunit = {'万', '亿'}; // 段名表示
        char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示
        String valStr = new BigDecimal(stringvalue).multiply(new BigDecimal("100")).toString();
        valStr = valStr.split("\\.")[0];
        int length = valStr.length();
        String head = "0"; // 默认整数部分是0
        String rail = null; // 小数部分
        if (length>2) {
            head = valStr.substring(0, valStr.length() - 2); // 取整数部分
            rail = valStr.substring(valStr.length() - 2); // 取小数部分
        } else {
            // 小数部分2位自动补0
            rail = StringUtils.leftPad(valStr,2, "0");
        }
        String prefix = ""; // 整数部分转化的结果
        String suffix = ""; // 小数部分转化的结果
        // 处理小数点后面的数
        if (zero2.equals(rail)) { // 如果小数部分为0
            suffix = sStr.equals(type) ? "份" : "整";
        } else {
            suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
            if (sStr.equals(type)) {
                suffix = "点" + suffix.replace("角", "").replace("分", "") + "份";
            }
        }
        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0' && idx != 0) { // 标志 ,连续零，仅读一次零，
                    zero = digit[0]; // 解决问题2,当一个零位于第0位时，不输出“零”，仅输出“段名”.
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            // 取到该位对应数组第几位。
            int position = chDig[i] - '0';
            if (!(position == 1 && i == 0 && idx == 1))// 解决问题3
            // ,即处理10读"拾",而不读"壹拾"
            {
                prefix += digit[position]; // 转化该数字表示
            }
            if (idx > 0) { // 段内位置表示的值
                prefix += hunit[idx - 1];
            }
            if (idx == 0 && vidx > 0) { // 段名表示的值
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }
        if (prefix.length() > 0 && !sStr.equals(type)) {
            // prefix += '圆'; // 如果整数部分存在,则有圆的字样
            prefix += '元';
        }
        return prefix + suffix; // 返回正确表示
    }
}
