package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

public class UserWithRef {
    public int id;
    public String name;
 
    @JsonBackReference
    public List<ItemWithRef> userItems;

    public UserWithRef(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addItem(ItemWithRef item) {
        synchronized (this){
            if(null==userItems){
                userItems=new ArrayList<>();
            }
            userItems.add(item);
        }
    }
}