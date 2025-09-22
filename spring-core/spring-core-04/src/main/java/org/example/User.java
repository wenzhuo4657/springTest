package org.example;

public class User {
    private String name;

    public void hello() {
        System.out.println("你好"+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
