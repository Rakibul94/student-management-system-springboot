package com.studentmanagementsystem.service;

import java.util.List;

import com.studentmanagementsystem.model.Student;

public interface StudentService {

    public Student addStudent(Student s);

    public Student updateStudent(Student s);

    public List<Student> getAllStudents();

    public Student getStudentById(Long id);

    public boolean deleteStudent(Long id);

}
