package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.DepartmentData;

import java.util.List;

public interface DepartmentServiceFacade {

    List<DepartmentData> getAllDepartments();
    DepartmentData getDepartmentById(Long id);
    DepartmentData createDepartment(DepartmentData departmentData);
    DepartmentData updateDepartment(DepartmentData departmentData);
    void deleteDepartmentById(Long id);

}