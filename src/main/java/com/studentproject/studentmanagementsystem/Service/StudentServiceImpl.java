package com.studentproject.studentmanagementsystem.Service;

import com.studentproject.studentmanagementsystem.Model.Student;
import com.studentproject.studentmanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentrepos;

    @Override
    public Student addStudent(Student s) {
        Student newstudent = studentrepos.save(s);
        return newstudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentrepos.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentrepos.findById(id).get();
    }

    @Override
    public boolean deleteStudent(int id) {
        Student s = studentrepos.findById(id).get();
        if (s != null) {
            studentrepos.delete(s);
            return true;
        }
        return false;

    }

    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }
}
