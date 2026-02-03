package com.rakibul.studentmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admins")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // Generates all-args constructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role = "ADMIN";

    private boolean enabled = true;


}
