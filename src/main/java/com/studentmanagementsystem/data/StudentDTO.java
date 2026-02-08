package com.studentmanagementsystem.data;


import com.studentmanagementsystem.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
public class StudentDTO {
    private Long id;
    private String name;
    private double cgpa;
    private String email;
    private String program;

    // relationship via IDs (NOT entity)
    private Long departmentId;
    private String departmentName;
}