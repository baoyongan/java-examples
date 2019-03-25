package com.baoyongan.java.eg.base.class_ch.classload;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与instanceof关键字演示
 *
 * @author zzm
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        testLoaders();

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                        return defineClass(name, b,0,b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("com.baoyongan.java.eg.base.class_ch.classload.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.baoyongan.java.eg.base.class_ch.classload.ClassLoaderTest);
    }

    private static void testLoaders() {
        System.out.println("get context class loader:"+Thread.currentThread().getContextClassLoader());

        ClassLoader classLoader=ClassLoaderTest.class.getClassLoader();
        System.out.println("classLoader*******"+classLoader);
        System.out.println("classLoader.getParent()*******"+classLoader.getParent());
        System.out.println("classLoader.getParent().getParent()*******"+classLoader.getParent().getParent());
        System.out.println();
        System.out.println(""+System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        System.out.println();
        System.out.println(java.util.HashMap.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
    }
}