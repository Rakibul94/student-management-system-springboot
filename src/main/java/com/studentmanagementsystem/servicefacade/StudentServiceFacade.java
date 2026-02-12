package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.StudentData;

import java.util.List;

public interface StudentServiceFacade {


    List<StudentData> getAllStudents();

    StudentData getStudentById(Long id);

    StudentData createStudent(StudentData studentData);
    StudentData updateStudent(StudentData studentData);

    void deleteStudentById(Long id);

}