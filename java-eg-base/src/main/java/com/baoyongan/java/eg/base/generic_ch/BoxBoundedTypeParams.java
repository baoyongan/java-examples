package com.baoyongan.java.eg.base.generic_ch;

public class BoxBoundedTypeParams<T> {

    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        BoxBoundedTypeParams<Integer> integerBox = new BoxBoundedTypeParams<Integer>();
        integerBox.set(new Integer(10));
        integerBox.inspect(1); // error: this is still String!
    }
}
