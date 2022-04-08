package com.baoyongan.java.eg.base.jaxb_ch;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JaxbTest {

    public static void main(String[] args) throws JAXBException {

        Person p=new Person("鲍永安",new Date(),1);

        List<Address> add=new ArrayList<>();
        add.add(new Address("北京朝阳","松榆里11号"));
        add.add(new Address("北京海淀","房里装1号"));
        p.setAddrs(add);
        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true); // 格式化输出，换行
        marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);// 省略头信息
        marshaller.marshal(p,System.out);
//        JAXB.marshal(p,System.out);
//        JAXB.marshal(p,new File("D:/tmp/person.xml"));
    }
}
