package com.studentproject.studentmanagementsystem.Model;

import jakarta.persistence.*;
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double cgpa;
    private String email;
    private String program;

    public Student() {} // Required by JPA

    public Student(String name, double cgpa, String email, String program) {
        this.name = name;
        this.cgpa = cgpa;
        this.email = email;
        this.program = program;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getCgpa() { return cgpa; }
    public String getEmail() { return email; }
    public String getProgram() { return program; }
}