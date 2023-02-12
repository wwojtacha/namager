package com.example.namager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AgifyModel {

    private int age;

    private String name;


    @JsonIgnore
    public boolean isValid() {
        return age > 0 && name != null;
    }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
