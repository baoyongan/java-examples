package com.baoyongan.java.eg.base.jaxb_ch;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {

    private String address;
    private String door;

    public Address() {
    }

    public Address(String address, String door) {
        this.address = address;
        this.door = door;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public String getDoor() {
        return door;
    }

    @XmlElement
    public void setDoor(String door) {
        this.door = door;
    }
}
