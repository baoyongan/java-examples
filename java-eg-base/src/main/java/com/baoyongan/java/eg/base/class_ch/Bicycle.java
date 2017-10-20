package com.baoyongan.java.eg.base.class_ch;

import java.util.HashMap;

public class Bicycle {

    // the Bicycle class has
    // three fields
    public int cadence; // 刹车
    public int gear; // 齿轮
    public int speed; // 速度

    // the Bicycle class has
    // one constructor
    public Bicycle(int startCadence, int startSpeed, int startGear) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
    }

    public Bicycle() {
    }

    // the Bicycle class has
    // four methods
    public void setCadence(int newValue) {
        cadence = newValue;
    }

    public void setGear(int newValue) {
        gear = newValue;
    }

    public void applyBrake(int decrement) {
        speed -= decrement;
    }

    public void speedUp(int increment) {
        speed += increment;
    }

    private void toSeee(String var1){
       var1=var1+"111";
        System.out.println(var1);
    }
    public static void main(String[] args) {
        Bicycle ss=new Bicycle();
        String vaar="bbbb";
        ss.toSeee(vaar);
        System.out.println(vaar);
    }

}