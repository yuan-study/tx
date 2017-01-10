package com.cglib.demo;

/**
 * Created by zhyy on 2017/1/8.
 */
public class Person {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void run(){
        System.err.println("人在run ......");
    }

}
