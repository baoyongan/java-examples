package com.baoyongan.java.eg.base;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class AppBig
{
    public static void main( String[] args )
    {
      User user= new User();
      BigDecimal n=new BigDecimal(23.33).setScale(2,BigDecimal.ROUND_HALF_UP);
      user.setAge(n);
        System.out.println(n);

        System.out.println(JSON.toJSONString(user));
    }

    static class User{
        private BigDecimal age;

        public BigDecimal getAge() {
            return age;
        }

        public void setAge(BigDecimal age) {
            this.age = age;
        }
    }
}
