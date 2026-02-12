package com.studentmanagementsystem.mapper;

import com.studentmanagementsystem.data.DepartmentData;
import com.studentmanagementsystem.data.StudentData;
import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.model.Student;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {


    /* ---------- Entity → DTO ---------- */

    public DepartmentData toData(Department department) {
        if (department == null) return null;

        DepartmentData departmentData = new DepartmentData();
        departmentData.setId(department.getId());
        departmentData.setName(department.getName());
        return departmentData;
    }

    /* ---------- DTO → Entity---------- */

    public Department toEntity(DepartmentData departmentData) {
        if (departmentData == null) return null;

        Department department = new Department();
        //No id is set as JPA generates id
        department.setName(departmentData.getName());
        return department;
    }

    public void updateEntity(Department department,
                             DepartmentData departmentData) {

        department.setName(departmentData.getName());

    }
}