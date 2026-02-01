package com.studentsystem.rakibul.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.rakibul.Model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
