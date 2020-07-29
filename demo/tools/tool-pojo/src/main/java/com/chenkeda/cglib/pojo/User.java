package com.chenkeda.cglib.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String phone;


    public User(String name) {
        this.name = name;
    }

    public User() {
        this.name = "ckd";
        hello("world");
    }

    public void hello(String word) {
        System.out.println(name + " say: hello, " + word);
    }
}
