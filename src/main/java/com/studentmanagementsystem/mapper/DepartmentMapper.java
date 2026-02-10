package com.studentmanagementsystem.mapper;

import com.studentmanagementsystem.data.DepartmentData;
import com.studentmanagementsystem.model.Department;
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

    /* ---------- DTO → Entity (partial) ---------- */

    public Department toEntity(DepartmentData departmentData) {
        if (departmentData == null) return null;

        Department department = new Department();
        department.setId(departmentData.getId());
        department.setName(departmentData.getName());
        return department;
    }
}