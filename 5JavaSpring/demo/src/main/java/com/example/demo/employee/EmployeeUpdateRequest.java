package com.example.demo.employee;

import com.example.demo.EmployeeCondition;

public class EmployeeUpdateRequest {
    private String firstName;
    private String lastName;
    private EmployeeCondition employeeCondition;
    private Integer birthYear;
    private Float salary;

    // getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeCondition getEmployeeCondition() {
        return employeeCondition;
    }

    public void setEmployeeCondition(EmployeeCondition employeeCondition) {
        this.employeeCondition = employeeCondition;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
