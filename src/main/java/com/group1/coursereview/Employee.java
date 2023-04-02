package com.group1.coursereview;


public class Employee {
    private int id;
    private String name;
    private static int counter = 0;

    public Employee(String s) {
        counter++;
        this.name = s;
        this.id = counter;
    }

    public int getId() {
        return this.id;
    }
    public String getName(){
        return this.name;
    }
}
