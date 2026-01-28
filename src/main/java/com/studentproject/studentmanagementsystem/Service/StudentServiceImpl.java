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
//    @Autowired
//    private StudentRepository studentRepository;

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
        if (student.getId() == null) {
            return null;
        }

        Student existing = studentRepository.findById(student.getId()).orElse(null);

        //Student existing = studentrepos.findById(id).get();

        if (existing == null) {
            return null;
        }

        // copy updated values
        existing.setStudentId(student.getStudentId());
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setCgpa(student.getCgpa());
        existing.setProgram(student.getProgram());

        return studentRepository.save(existing); // UPDATE guaranteed
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).get();
    }



    @Override
    public boolean deleteStudent(int id) {
        Student student = studentRepository.findById(id).get();
        if (student != null) {
            studentRepository.delete(student);
            return true;
        }
        return false;

    }

    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("message");
    }
}
