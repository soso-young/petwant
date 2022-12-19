package com.example.petregisterapplication;

public class Student {
    String id;
    String name;

    public Student(String name) {
    }

    public Student(String id,String name) {
        this.id = id;
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

