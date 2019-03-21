package com.baoyongan.bean;

public class Foo {

    private String location;

    public void handleLocation(){
        System.out.println("location: "+location);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
