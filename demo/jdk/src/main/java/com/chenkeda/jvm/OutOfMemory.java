package com.chenkeda.jvm;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.stream.Stream;

public class OutOfMemory {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        Stream.iterate(1, i -> ++i).forEach(integer -> list.add(new A(integer + "")));
    }
    @Data
    @AllArgsConstructor
    static class A{
        private String name;
    }
}
