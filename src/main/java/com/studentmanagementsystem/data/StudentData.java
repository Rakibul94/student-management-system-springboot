package com.studentmanagementsystem.data;


import lombok.Data;


@Data
public class StudentData {
    private Long id;
    private String name;
    private double cgpa;
    private String email;
    private String program;


    private Long departmentId;
    private String departmentName;
}