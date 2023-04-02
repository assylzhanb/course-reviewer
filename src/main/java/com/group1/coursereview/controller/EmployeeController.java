package com.group1.coursereview.controller;

import com.group1.coursereview.Employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @GetMapping("/employees")

    // GET requests for all employees
    public Employee[] getEmployees(){
        Employee assyl = new Employee();
        Employee mashina = new Employee();

        return new Employee[]{assyl, mashina};
    }

    // GET requests for single employee with their id
//    @GetMapping("/employees/{id}")
//    public Employee getEmployee(@PathVariable Long id){
//        return employee;
//    }
//    // POST requests for creating employee
//    @PostMapping("/employees"){
//    public Employee createEmployee(@RequestBody Employee employee){
//        return employee;
//    }
//
//    // PUT requests for updating an existing employee u know
//    @PutMapping("/employees")
//    public Employee updateEmployee(@RequestBody Employee employee) {
//
//        return employee;
//    }

}
