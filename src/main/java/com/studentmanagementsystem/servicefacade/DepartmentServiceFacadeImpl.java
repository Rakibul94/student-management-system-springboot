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
                .map(this::toData)
                .toList();
    }

    @Override
    public DepartmentData getDepartmentById(Long id) {
        Department department = departmentService.getDepartmentById(id);
        return department == null ? null : toData(department);
    }

    @Override
    public DepartmentData createDepartment(String name) {
        if (name == null || name.isBlank()) return null;

        Department department = new Department();
        department.setName(name);

        return toData(departmentService.save(department));
    }

    @Override
    public DepartmentData updateDepartment(DepartmentData departmentData) {
        Department existingDepartment =
                departmentService.getDepartmentById(departmentData.getId());

        if (existingDepartment == null) return null;

        existingDepartment.setName(departmentData.getName());
        return toData(departmentService.updateDepartment(existingDepartment));
    }

    @Override
    public boolean deleteDepartment(Long id) {
        return departmentService.deleteDepartment(id);
    }

    private DepartmentData toData(Department department) {
        DepartmentData departmentData = new DepartmentData();
        departmentData.setId(department.getId());
        departmentData.setName(department.getName());
        return departmentData;
    }


}