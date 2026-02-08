package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.StudentDTO;
import com.studentmanagementsystem.model.Student;

import java.util.List;

public interface StudentServiceFacade {

//    List<Student> getAllStudents();
//
//    Student getStudentById(Long id);
//
//    Student createStudent(Student student, Long departmentId);
//
//    Student updateStudent(Student student, Long departmentId);
//
//    boolean deleteStudent(Long studentId);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createStudent(StudentDTO dto);

    StudentDTO updateStudent(StudentDTO dto);

    boolean deleteStudent(Long id);

}