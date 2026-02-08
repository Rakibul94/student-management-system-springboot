package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.DepartmentDTO;
import com.studentmanagementsystem.model.Department;

import java.util.List;

public interface DepartmentServiceFacade {

//    List<Department> getAllDepartments();
//
//    Department findOrCreateByName(String name);
//
//    Department getDepartmentById(Long id);
//
//    public Department updateDepartment(Department department , Long departmentId);
//
//    public boolean deleteDepartment(Long departmentId);

    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentById(Long id);
    DepartmentDTO createDepartment(String name);
    DepartmentDTO updateDepartment(DepartmentDTO dto);
    boolean deleteDepartment(Long id);

}