package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.service.DepartmentService;
import org.springframework.stereotype.Service;
import com.studentmanagementsystem.data.DepartmentData;

import java.util.List;

@Service
public class DepartmentServiceFacadeImpl implements DepartmentServiceFacade {

    private final DepartmentService departmentService;

    public DepartmentServiceFacadeImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @Override
    public List<DepartmentData> getAllDepartments() {
        return departmentService.getAllDepartments()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public DepartmentData getDepartmentById(Long id) {
        Department department = departmentService.getDepartmentById(id);
        return department == null ? null : toDTO(department);
    }

    @Override
    public DepartmentData createDepartment(String name) {
        if (name == null || name.isBlank()) return null;

        Department department = new Department();
        department.setName(name);

        return toDTO(departmentService.save(department));
    }

    @Override
    public DepartmentData updateDepartment(DepartmentData departmentData) {
        Department existing =
                departmentService.getDepartmentById(departmentData.getId());

        if (existing == null) return null;

        existing.setName(departmentData.getName());
        return toDTO(departmentService.updateDepartment(existing));
    }

    @Override
    public boolean deleteDepartment(Long id) {
        return departmentService.deleteDepartment(id);
    }

    private DepartmentData toDTO(Department department) {
        DepartmentData departmentData = new DepartmentData();
        departmentData.setId(department.getId());
        departmentData.setName(department.getName());
        return departmentData;
    }


}