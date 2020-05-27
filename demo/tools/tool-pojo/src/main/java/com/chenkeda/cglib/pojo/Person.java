package com.chenkeda.cglib.pojo;

import lombok.Data;

import java.beans.ConstructorProperties;

@Data
public class Person {
    private String firstname;
    private String lastname;
    private PhoneNumber phone;
    private PhoneNumber fax;

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}