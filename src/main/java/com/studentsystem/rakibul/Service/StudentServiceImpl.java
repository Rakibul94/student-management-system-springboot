package com.studentsystem.rakibul.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.studentsystem.rakibul.Model.Student;
import com.studentsystem.rakibul.Repository.StudentRepository;

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

        return studentRepository.save(student); // UPDATE guaranteed
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

    public void removeSessionMessage() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            return; // no request context
        }

        HttpSession session = attributes.getRequest().getSession(false);
        if (session == null) {
            return; // no session exists
        }

        session.removeAttribute("message");


    }


}
