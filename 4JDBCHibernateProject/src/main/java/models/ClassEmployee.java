package models;

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

    @OneToMany(mappedBy = "classEmployee", cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "classEmployee", cascade = CascadeType.ALL)
    private List<Employee> rates = new ArrayList<>();

    @Column(name = "maxEmployeeListSize", nullable = false)
    private int maxEmployeeListSize;

    public ClassEmployee() {}

    public ClassEmployee(String groupName, int maxEmployeeListSize) {
        this.groupName = groupName;
        this.maxEmployeeListSize = maxEmployeeListSize;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double calculateOccupancyRate() {
        return employees.size() / maxEmployeeListSize * 100;
    }


}