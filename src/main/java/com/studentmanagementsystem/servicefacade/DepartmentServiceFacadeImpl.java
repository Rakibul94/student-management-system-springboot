package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceFacadeImpl implements DepartmentServiceFacade {

    private final DepartmentService departmentService;

    public DepartmentServiceFacadeImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    public Department findOrCreateByName(String name) {

        if (name == null || name.isBlank()) {
            return null;
        }

        Department department = departmentService.findByName(name);

        if (department == null) {
            department = new Department();
            department.setName(name);
            department = departmentService.save(department);
        }

        return department;
    }

    public Department getDepartmentById(Long id) {
        return departmentService.getDepartmentById(id);
    }

    @Transactional
    public Department updateDepartment(Department department , Long departmentId) {



        // fetch existing student
        Department existingDepartment = departmentService.getDepartmentById(departmentId);
        if (existingDepartment == null) {
            return null;
        }


        // update fields
        existingDepartment.setName(department.getName());

        return departmentService.updateDepartment(existingDepartment);
    }


    @Transactional
    public boolean deleteDepartment(Long departmentId) {
        if (departmentId == null) {
            return false;
        }

        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null) {
            return false;
        }

        departmentService.deleteDepartment(departmentId);
        return true;
    }


}