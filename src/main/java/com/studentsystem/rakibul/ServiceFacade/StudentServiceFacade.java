package com.studentsystem.rakibul.ServiceFacade;

import com.studentsystem.rakibul.Model.Student;

import java.util.List;

public interface StudentServiceFacade {

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student createStudent(Student student, String departmentName);

    Student updateStudent(Student student, String departmentName);

    boolean deleteStudent(Long studentId);

}