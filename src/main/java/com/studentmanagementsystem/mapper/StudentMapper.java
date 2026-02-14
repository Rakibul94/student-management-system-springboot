package com.studentmanagementsystem.mapper;

import com.studentmanagementsystem.data.DepartmentData;
import com.studentmanagementsystem.data.StudentData;
import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    /* ---------- Entity → DTO ---------- */

    public StudentData toData(Student student) {
        if (student == null) return null;


        StudentData studentData = new StudentData();
        studentData.setId(student.getId());
        studentData.setName(student.getName());
        studentData.setEmail(student.getEmail());
        studentData.setCgpa(student.getCgpa());
        studentData.setProgram(student.getProgram());
        studentData.setDepartmentId(student.getDepartment().getId());
        studentData.setDepartmentName(student.getDepartment().getName());

        return studentData;
    }

    /* ---------- DTO → Entity ---------- */

    public Student toEntity(StudentData studentData) {
        if (studentData == null) return null;
        //No id is set as JPA generates id
        Student student = new Student();
        student.setName(studentData.getName());
        student.setEmail(studentData.getEmail());
        student.setCgpa(studentData.getCgpa());
        student.setProgram(studentData.getProgram());

        Department department = new Department();
        department.setId(studentData.getDepartmentId());
        student.setDepartment(department);


        return student;
    }

    /* ---------- Update existing entity ---------- */

    public void updateEntity(Student student,
                             StudentData studentData) {

        student.setName(studentData.getName());
        student.setEmail(studentData.getEmail());
        student.setCgpa(studentData.getCgpa());
        student.setProgram(studentData.getProgram());

        Department department = new Department();
        department.setId(studentData.getDepartmentId());
        student.setDepartment(department);
    }


}