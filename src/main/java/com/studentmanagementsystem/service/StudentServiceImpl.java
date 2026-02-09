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
    public Student addStudent(Student student) {
        if (student == null) {
            return null;
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {


        if (student == null || student.getId() == null) {
            return null;
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        if (id == null) {
            return null;
        }

        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteStudent(Long id) {
        if (id == null) {
            return false;
        }
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;

    }

}
