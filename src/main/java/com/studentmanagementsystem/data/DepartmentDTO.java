package com.studentmanagementsystem.data;


import com.studentmanagementsystem.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Generates getters, setters, toString, equals, hashCode
public class DepartmentDTO {
    private Long id;
    private String name;
}