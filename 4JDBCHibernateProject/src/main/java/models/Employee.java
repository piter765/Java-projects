package models;

import models.ClassEmployee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="employees")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "classEmployeeId")
    private ClassEmployee classEmployee;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName", nullable = false)
    private String lastName;

    @Column(name="employeeCondition", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeCondition employeeCondition;

    @Column(name="birthYear", nullable = false)
    private int birthYear;

    @Column(name="salary", nullable = false)
    private float salary;


    public Employee() {}

    public Employee(String firstName, String lastName, EmployeeCondition employeeCondition, int birthYear, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeCondition = employeeCondition;
        this.birthYear = birthYear;
        this.salary = salary;
        this.classEmployee = null;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassEmployee getClassEmployee() {
        return this.classEmployee;
    }

    public void setClass(ClassEmployee classEmployee) {
        this.classEmployee = classEmployee;
    }

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

    @Override
    public String toString() {
        return "models.Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeCondition='" + employeeCondition + '\'' +
                ", birthYear=" + birthYear +
                ", salary=" + salary +
                '}';
    }
}