package com.studentproject.studentmanagementsystem.Service;

import com.studentproject.studentmanagementsystem.Model.Student;
import com.studentproject.studentmanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentrepos;

    @Override
    public List<Student> getAllStudents() {
        return studentrepos.findAll();
    }
}
