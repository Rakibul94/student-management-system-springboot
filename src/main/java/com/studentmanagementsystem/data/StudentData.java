package com.studentmanagementsystem.data;


import lombok.Data;


@Data
public class StudentData {
    private Long id;
    private String name;
    private double cgpa;
    private String email;
    private String program;

    //We want only want the required attributes of DepartmentData
    //not expose the entire department entity for leakage
    private Long departmentId;
    private String departmentName;
}