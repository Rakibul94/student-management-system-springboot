package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.StudentData;

import java.util.List;

public interface StudentServiceFacade {


    List<StudentData> getAllStudents();

    StudentData getStudentById(Long id);

    StudentData createStudent(StudentData data);

    StudentData updateStudent(StudentData data);

    void deleteStudentById(Long id);

}