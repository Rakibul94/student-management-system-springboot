package com.rakibul.studentmanagementsystem.service;
import com.rakibul.studentmanagementsystem.model.Department;
import com.rakibul.studentmanagementsystem.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findByName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        return departmentRepository.findByNameIgnoreCase(name).orElse(null);
    }

    @Override
    public Department save(Department department) {
        if (department == null) {
            return null;
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {


        if (department == null || department.getId() == null) {
            return null;
        }

        return departmentRepository.save(department);
    }

    @Override
    public boolean deleteDepartment(Long id) {
        if (id == null) {
            return false;
        }
        Department department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;

    }



}