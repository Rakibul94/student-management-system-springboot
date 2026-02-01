package com.studentsystem.rakibul.Service;

import com.studentsystem.rakibul.Model.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
    Department findByName(String name);
    Department save(Department department);
}
