package com.chenkeda.cglib.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author chenkeda
 */
@Data
public class Employee {
    private Address address;
    private List<Employee> subordinateList;
    private String firstName;
    private String lastName;
}
@Data
class Address {
    private String street;
    private String number;
}