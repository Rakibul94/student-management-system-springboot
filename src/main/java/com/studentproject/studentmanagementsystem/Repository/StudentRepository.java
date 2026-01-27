package com.studentproject.studentmanagementsystem.Repository;

import com.studentproject.studentmanagementsystem.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}