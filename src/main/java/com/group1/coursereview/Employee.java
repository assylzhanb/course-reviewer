package com.group1.coursereview;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String role;

    public Employee(){}

    public Employee(Long id, String name, String role){
        this.id = id;
        this.name = name;
        this.role = role;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return this.name;
    }
    public String getRole(){
        return role;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setRole(String role){
        this.role = role;
    }


}
