package com.baoyongan.java.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * TODO
 *
 * @author baoya49764
 * @date 2023/7/12 20:07
 */
public class JsonApp {

    public static void main(String[] args) {
        String s="[]";
        String o="{}";
        Object duixiang = JSON.parse(o);
        print(duixiang);
        Object shuzu = JSON.parse(s);
        print(shuzu);

    }

    private static void print(Object sparse) {
        if(sparse instanceof JSONArray){
            System.out.println("是数组"+sparse);
            JSONArray s= (JSONArray) sparse;
            System.out.println("是数组"+s);
        } else if(sparse instanceof JSONObject){
            System.out.println("是对象"+sparse);
            JSONObject s= (JSONObject) sparse;
            System.out.println("是对象"+s);
        }
    }
}
