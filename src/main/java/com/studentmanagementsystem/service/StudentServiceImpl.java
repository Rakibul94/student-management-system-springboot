package com.studentmanagementsystem.service;

import org.springframework.stereotype.Service;
import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteStudent(Long id) {
         studentRepository.deleteById(id);
    }



}
