package com.example.mibanco.exchangerate.models.entity;

import java.util.List;


public class
LargeRequestBody {
    private String name;
    private List<String> data;

    public LargeRequestBody(String name, List<String> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public List<String> getData() {
        return data;
    }
}