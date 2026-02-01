package com.studentsystem.rakibul.ServiceFacade;

import com.studentsystem.rakibul.Model.Department;
import com.studentsystem.rakibul.Service.DepartmentService;
import org.springframework.stereotype.Service;

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

    public Department getById(Long id) {
        return departmentService.getDepartmentById(id);
    }


}