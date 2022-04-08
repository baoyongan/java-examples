package com.baoyongan.java.eg.base.jaxb_ch;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateXmlAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {
        if (null == v || "".equals(v.trim())) {
            return null;
        } else {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(v);
        }
    }

    @Override
    public String marshal(Date v) throws Exception {
        if(null==v){
            return null;
        }else {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(v);
        }
    }
}
