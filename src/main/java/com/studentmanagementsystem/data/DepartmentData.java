package com.studentmanagementsystem.data;


import lombok.Data;

@Data // Generates getters, setters, toString, equals, hashCode
public class DepartmentData {
    private Long id;
    private String name;
}