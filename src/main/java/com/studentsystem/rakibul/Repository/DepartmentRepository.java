package com.studentsystem.rakibul.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.rakibul.Model.Department;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByNameIgnoreCase(String name);
}
