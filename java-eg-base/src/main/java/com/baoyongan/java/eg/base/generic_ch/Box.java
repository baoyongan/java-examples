package com.baoyongan.java.eg.base.generic_ch;

/**
 * 没有引入泛型前，Box 支持放入各种类型的对象，但无法确定具体是什么类型的对象。
 */
public class Box {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }

    public static void main(String[] args) {
        Box s=new Box();
        s.set("ss");
        String o = (String) s.get(); // 容易在运行期，发生类型转换异常。因为不确定具体放入Box 的对象类型。
        System.out.println(o);
    }
}
