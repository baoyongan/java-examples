package com.baoyongan.java.eg.base;

import java.util.ArrayList;
import java.util.List;

/*
 * Created with Intellij IDEA
 * USER: 焦一平
 * Date: 2015/10/25
 * Time: 10:41
 * To change this template use File | Settings | File Template
 */
public class TestInstanceCodeBlock {
    //实例化代码块儿：
    //
    //和静态代码块儿的概念相对应，静态代码块儿是static 关键字 + 大括号，把静态代码块儿的static关键字去掉就是实例化代码块儿,静态代码块儿在类初始化的时候执行一次
    //
    //而实例化代码块儿在每次生成对象的时候都会执行(实例化代码块儿会先于构造方法执行)。
    //
    //使用匿名内部类进行初始化：在new 一个对象的时候，小括号后边跟一个大括号
    //
    //使用匿名内部类 + 实例化代码块儿 = 使用两个大括号进行初始化

    public static void main(final String[] args) {

        //匿名内部类
        Person person = new Person("张三"){
            @Override
            public String getName() {
                return super.getName()+"123";
            }
        };
        System.out.println(person.getName());

        //两个大括号的方式初始化(本质上是匿名内部类 + 实例化代码块儿)
        List<String> personList = new ArrayList<String>(){{
            add("AA");
            add("BB");
            add("CC");

        }};
        for (String s : personList){
            System.out.println(s);
        }
    }
}


class Person{
    String name;
    public Person(String name){
        this.name = name;
        System.out.println("构造方法执行...");
    }
    //实例化代码块儿,先于构造方法执行
    {
        System.out.println("实例初始化...");
    }
    public String getName(){
        return name;
    }

}