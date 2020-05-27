package com.chenkeda.xstream;

import com.chenkeda.cglib.pojo.Person;
import com.chenkeda.cglib.pojo.PhoneNumber;
import com.thoughtworks.xstream.XStream;

public class XstreamDemo {
    public static void main(String[] args) {
        XStream xStream = new XStream();
//        xStream.alias("persion", Person.class);
//        xStream.alias
        Person joe = new Person("Joe", "Walnes");
        joe.setPhone(new PhoneNumber(123, "1234-456"));
        joe.setFax(new PhoneNumber(123, "9999-999"));

        String joeXml = xStream.toXML(joe);
        System.out.println(joeXml);
        joeXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + joeXml;
        Object o = xStream.fromXML(joeXml);
        System.out.println(o);
    }
}
