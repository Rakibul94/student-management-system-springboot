package com.rakibul.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakibul.studentmanagementsystem.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
