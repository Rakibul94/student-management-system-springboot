package com.rakibul.studentmanagementsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "departments")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // Generates all-args constructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Student> studentList;



}