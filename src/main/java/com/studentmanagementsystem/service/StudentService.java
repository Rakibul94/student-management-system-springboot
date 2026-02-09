package com.studentmanagementsystem.service;

import java.util.List;

import com.studentmanagementsystem.model.Student;

public interface StudentService {

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public List<Student> getAllStudents();

    public Student getStudentById(Long id);

    public boolean deleteStudent(Long id);

}
