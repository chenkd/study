package com.chenkeda.cglib.pojo;

import lombok.Data;

@Data
public class PhoneNumber {
    private int code;
    private String number;

    public PhoneNumber(int code, String number) {
        this.code = code;
        this.number = number;
    }
}
