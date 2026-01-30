package com.studentsystem.rakibul.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.rakibul.Model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
