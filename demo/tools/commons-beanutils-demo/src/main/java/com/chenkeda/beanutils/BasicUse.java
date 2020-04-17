package com.chenkeda.beanutils;

import com.chenkeda.cglib.pojo.Employee;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class BasicUse {
    public static void main(String[] args) {
        Employee employee = new Employee();

        try {
            PropertyUtils.setProperty(employee, "firstName", "chen");
            Object firstName = PropertyUtils.getSimpleProperty(employee, "firstName");
            System.out.println(firstName);


        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
