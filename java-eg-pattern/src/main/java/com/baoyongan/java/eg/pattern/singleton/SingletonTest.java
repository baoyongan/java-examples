package com.baoyongan.java.eg.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonTest {
    public static void main(String[] args) {
        // 破坏单例，通过反射调用私有构造器生成两个实例
        brokenSingleton();
        // 破坏单例，本地序列化一次，然后再反序列化，这样可以产生两个实例。

        // 使用枚举实现单例。
        Elvis.INSTANCE.say();
    }

    private static void brokenSingleton() {
        SingletonTy instance = SingletonTy.getInstance();
        Class aClass = null;
        try {
            aClass = Class.forName("com.baoyongan.java.eg.pattern.singleton.SingletonTy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (aClass == null) {
            System.out.println("Class.forName 失败");
            return;
        }
        Constructor<SingletonTy>[] declaredConstructors = aClass.getDeclaredConstructors();
        Constructor<SingletonTy> _default = null;
        if (null != declaredConstructors
                && declaredConstructors.length > 0) {
            _default = declaredConstructors[0];
        }
        if (_default == null) {
            System.out.println("没有申明的构造器");
            return;
        }
        boolean accessible = _default.isAccessible();
        System.out.println("默认的 accessible: " + accessible);
        _default.setAccessible(true); // 如果不设置为 ture 私有的构造器是无法访问的。
        SingletonTy _instance = null;
        try {
            _instance = _default.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (null == _instance) {
            System.out.println("反射实例化单例对象失败。");
            return;
        }
        _instance.say("world");
        System.out.println("实例1" + instance);
        System.out.println("实例2" + _instance);
    }
}
