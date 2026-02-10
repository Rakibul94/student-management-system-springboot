package com.studentmanagementsystem.service;

import com.studentmanagementsystem.model.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id);

    List<Department> getAllDepartments();

    Department findByName(String name);

    Department save(Department department);

    public boolean deleteDepartment(Long id);
}
