package com.studentmanagementsystem.mapper;

import com.studentmanagementsystem.data.DepartmentData;
import com.studentmanagementsystem.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {


    /* ---------- Entity → DTO ---------- */

    public DepartmentData toData(Department department) {
        if (department == null) return null;

        DepartmentData data = new DepartmentData();
        data.setId(department.getId());
        data.setName(department.getName());
        return data;
    }

    /* ---------- DTO → Entity (partial) ---------- */

    public Department toEntity(DepartmentData data) {
        if (data == null) return null;

        Department department = new Department();
        department.setId(data.getId());
        department.setName(data.getName());
        return department;
    }
}