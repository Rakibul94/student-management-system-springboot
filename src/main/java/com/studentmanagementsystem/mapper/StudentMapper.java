package com.studentmanagementsystem.mapper;

import com.studentmanagementsystem.data.StudentData;
import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    /* ---------- Entity → DTO ---------- */

    public StudentData toData(Student student) {
        if (student == null) return null;

        StudentData data = new StudentData();
        data.setId(student.getId());
        data.setName(student.getName());
        data.setEmail(student.getEmail());
        data.setCgpa(student.getCgpa());
        data.setProgram(student.getProgram());

        if (student.getDepartment() != null) {
            data.setDepartmentId(student.getDepartment().getId());
            data.setDepartmentName(student.getDepartment().getName());
        }

        return data;
    }

    /* ---------- DTO → Entity (partial) ---------- */

    public Student toEntity(StudentData data) {
        if (data == null) return null;

        Student student = new Student();
        student.setName(data.getName());
        student.setEmail(data.getEmail());
        student.setCgpa(data.getCgpa());
        student.setProgram(data.getProgram());
        return student;
    }

    /* ---------- Update existing entity ---------- */

    public void updateEntity(Student student,
                             StudentData data,
                             Department department) {

        student.setName(data.getName());
        student.setEmail(data.getEmail());
        student.setCgpa(data.getCgpa());
        student.setProgram(data.getProgram());
        student.setDepartment(department);
    }
}