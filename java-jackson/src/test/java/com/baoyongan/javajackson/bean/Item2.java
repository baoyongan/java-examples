package com.baoyongan.javajackson.bean;

public class Item2 {
    public int id;
    public String itemName;
    public User owner;

    public Item2(int id, String itemName, User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
}