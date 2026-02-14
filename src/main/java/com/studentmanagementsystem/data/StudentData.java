package com.studentmanagementsystem.data;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // Generates all-args constructor
public class StudentData {
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Cgpa cannot be empty")
    @DecimalMin(value = "0.0", message = "CGPA must be at least 0.0")
    @DecimalMax(value = "4.0", message = "CGPA must be at most 4.0")
    private Double cgpa;
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "Program cannot be empty")
    private String program;

    @NotNull(message = "Department cannot be empty")
    private Long departmentId;
    private String departmentName;


}