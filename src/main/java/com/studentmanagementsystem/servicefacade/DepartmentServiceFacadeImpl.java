package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.mapper.DepartmentMapper;
import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.service.DepartmentService;
import org.springframework.stereotype.Service;
import com.studentmanagementsystem.data.DepartmentData;

import java.util.List;

@Service
public class DepartmentServiceFacadeImpl implements DepartmentServiceFacade {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceFacadeImpl(DepartmentService departmentService,
                                       DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }


    @Override
    public List<DepartmentData> getAllDepartments() {
        return departmentService.getAllDepartments()
                .stream()
                .map(departmentMapper::toData)
                .toList();
    }

    @Override
    public DepartmentData getDepartmentById(Long id) {
        if (id == null) return null;

        Department department = departmentService.getDepartmentById(id);
        return departmentMapper.toData(department);
    }

    @Override
    public DepartmentData createDepartment(String name) {
        if (name == null || name.isBlank()) return null;

        Department department = new Department();
        department.setName(name);

        return departmentMapper.toData(departmentService.save(department));
    }

    @Override
    public DepartmentData updateDepartment(DepartmentData departmentData) {
        Department existingDepartment =
                departmentService.getDepartmentById(departmentData.getId());

        if (existingDepartment == null) return null;

        existingDepartment.setName(departmentData.getName());
        return departmentMapper.toData(departmentService.save(existingDepartment));
    }

    @Override
    public boolean deleteDepartment(Long id) {
        return departmentService.deleteDepartment(id);
    }





}