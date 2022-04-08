package com.baoyongan.java.eg.base.jaxb_ch;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.NONE) // 只显示指定的元素
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED) // 不起作用
public class Person {

    private String name;
    private Date birth;
    private int gender;
    private List<Address> addrs;

    public Person() {
    }

    public Person(String name, Date birth, int gender) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    @XmlJavaTypeAdapter(DateXmlAdapter.class)
    @XmlElement
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getGender() {
        return gender;
    }

    @XmlElement
    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<Address> getAddrs() {
        return addrs;
    }

    @XmlElementWrapper(name = "addrs")
    @XmlElement(name = "addr")
    public void setAddrs(List<Address> addrs) {
        this.addrs = addrs;
    }
}
