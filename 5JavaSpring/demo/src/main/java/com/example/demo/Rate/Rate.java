package com.example.demo.Rate;

import com.example.demo.classEmployee.ClassEmployee;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rates")
public class Rate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value", nullable = false)
    private int value;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "classEmployeeId")
    private ClassEmployee classEmployee;

    @Column
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Rate() {}

    public Rate(int value, ClassEmployee classEmployee, String comment) {
        this.value = value;
        this.classEmployee = classEmployee;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ClassEmployee getClassEmployee() {
        return classEmployee;
    }

    public void setClassEmployee(ClassEmployee classEmployee) {
        this.classEmployee = classEmployee;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
