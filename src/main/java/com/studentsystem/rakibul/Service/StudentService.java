package com.studentsystem.rakibul.Service;

import java.util.List;

import com.studentsystem.rakibul.Model.Student;

public interface StudentService {

    public Student addStudent(Student s);

    public Student updateStudent(Student s);

    public List<Student> getAllStudents();

    public Student getStudentById(Long id);

    public boolean deleteStudent(Long id);

}
