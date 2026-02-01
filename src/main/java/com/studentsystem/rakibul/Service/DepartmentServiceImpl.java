package com.studentsystem.rakibul.Service;

import com.studentsystem.rakibul.Model.Department;
import com.studentsystem.rakibul.Repository.DepartmentRepository;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentById(Long id) {
        if (id == null) {
            return null;
        }
        return departmentRepository.findById(id).orElse(null);
    }
}