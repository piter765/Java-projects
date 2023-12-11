package com.example.demo.employee;

import com.example.demo.EmployeeCondition;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private EmployeeCondition employeeCondition;
    private Integer birthYear;
    private Float salary;

    // getters and setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeCondition getEmployeeCondition() {
        return employeeCondition;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public float getSalary() {
        return salary;
    }

}
