package com.example.demo.classEmployee;

import com.example.demo.Rating.Rating;
import com.example.demo.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="class_employees")
public class ClassEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "groupName", nullable = false)
    private String groupName;

    @JsonIgnore
    @OneToMany(mappedBy = "classEmployee")
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "classEmployee", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    @Column(name = "maxEmployeeListSize", nullable = false)
    private int maxEmployeeListSize;


    public ClassEmployee() {}

    public ClassEmployee(String groupName, int maxEmployeeListSize) {
        this.groupName = groupName;
        this.maxEmployeeListSize = maxEmployeeListSize;
    }

    // Getters and setters

    public int getId() { return this.id; }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public int getMaxEmployeeListSize() {
        return maxEmployeeListSize;
    }

    public void setMaxEmployeeListSize(int maxEmployeeListSize) {
        this.maxEmployeeListSize = maxEmployeeListSize;
    }

    public double getFill() {
        if (maxEmployeeListSize == 0) {
            return 0.0;
        }

        return employees.size() / maxEmployeeListSize * 100;
    }


}
