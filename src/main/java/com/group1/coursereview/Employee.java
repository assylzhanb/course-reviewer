package com.group1.coursereview;


public class Employee {
    private int id;
    private static int counter = 0;

    public Employee() {
        counter++;
        this.id = counter;
    }

    public int getId() {
        return id;
    }
}
