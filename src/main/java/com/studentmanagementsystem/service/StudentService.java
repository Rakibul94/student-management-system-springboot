package com.studentmanagementsystem.service;

import java.util.List;

import com.studentmanagementsystem.model.Student;

public interface StudentService {

    public Student saveStudent(Student student);

    public List<Student> getAllStudents();

    public Student getStudentById(Long id);

    public void deleteStudent(Long id);

}
