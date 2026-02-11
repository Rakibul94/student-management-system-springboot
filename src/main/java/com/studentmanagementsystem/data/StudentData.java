package com.studentmanagementsystem.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // Generates all-args constructor
public class StudentData {
    private Long id;
    private String name;
    private Double cgpa;
    private String email;
    private String program;

    //We want only want the required attributes of DepartmentData
    //not expose the entire department entity for leakage
    private Long departmentId;
    private String departmentName;
}