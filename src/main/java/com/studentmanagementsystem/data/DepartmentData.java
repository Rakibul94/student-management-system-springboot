package com.studentmanagementsystem.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data// Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // Generates all-args constructor
public class DepartmentData {
    private Long id;

    @NotBlank(message = "Department name cannot be empty")
    private String name;
}