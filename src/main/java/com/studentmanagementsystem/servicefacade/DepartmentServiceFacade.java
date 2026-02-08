package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.DepartmentData;

import java.util.List;

public interface DepartmentServiceFacade {

    List<DepartmentData> getAllDepartments();
    DepartmentData getDepartmentById(Long id);
    DepartmentData createDepartment(String name);
    DepartmentData updateDepartment(DepartmentData dto);
    boolean deleteDepartment(Long id);

}