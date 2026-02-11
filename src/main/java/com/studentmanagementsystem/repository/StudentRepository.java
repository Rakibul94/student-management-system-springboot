package com.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmanagementsystem.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
