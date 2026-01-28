package com.studentproject.studentmanagementsystem.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "student_id", unique = true, nullable = false)
    private int studentId;
    private String name;
    private double cgpa;
    private String email;
    private String program;

    public Student() {
    } // Required by JPA

    public Student(Integer id, int studentId, String name, double cgpa, String email, String program) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.cgpa = cgpa;
        this.email = email;
        this.program = program;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public String getEmail() {
        return email;
    }

    public String getProgram() {
        return program;
    }

    //
    public void setId(Integer id) {
        this.id = id;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProgram(String program) {
        this.program = program;
    }

}