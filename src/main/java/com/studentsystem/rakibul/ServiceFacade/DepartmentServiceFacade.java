package com.studentsystem.rakibul.ServiceFacade;

import com.studentsystem.rakibul.Model.Department;

import java.util.List;

public interface DepartmentServiceFacade {

    List<Department> getAllDepartments();

    Department findOrCreateByName(String name);

    Department getById(Long id);

}