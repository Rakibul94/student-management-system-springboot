package com.rakibul.studentmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rakibul.studentmanagementsystem.model.Student;
import com.rakibul.studentmanagementsystem.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
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
            studentRepository.delete(student);
            return true;
        }
        return false;

    }




}
