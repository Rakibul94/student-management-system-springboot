package com.studentmanagementsystem.servicefacade;

import org.springframework.stereotype.Service;

import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.service.DepartmentService;
import com.studentmanagementsystem.service.StudentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceFacadeImpl implements StudentServiceFacade{
    private final StudentService studentService;
    private final DepartmentService departmentService;

    public StudentServiceFacadeImpl(StudentService studentService,
                                    DepartmentService departmentService) {
        this.studentService = studentService;
        this.departmentService = departmentService;
    }



    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public Student getStudentById(Long id) {
        return studentService.getStudentById(id);
    }



    @Transactional
    public Student updateStudent(Student student, Long departmentId) {

        if (departmentId == null) {
            return null;
        }

        Student existingStudent =
                studentService.getStudentById(student.getId());

        if (existingStudent == null) {
            return null;
        }

        Department department =
                departmentService.getDepartmentById(departmentId);

        if (department == null) {
            return null;
        }

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCgpa(student.getCgpa());
        existingStudent.setProgram(student.getProgram());
        existingStudent.setDepartment(department);

        return studentService.updateStudent(existingStudent);
    }

    public Student createStudent(Student student, Long departmentId) {

        if (departmentId == null) {
            return null;
        }


        // create department if it doesn't exist
        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null) {
            return null; // invalid selection
        }

        student.setDepartment(department);
        return studentService.addStudent(student);

    }


    @Transactional
    public boolean deleteStudent(Long studentId) {
        if (studentId == null) {
            return false;
        }

        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return false;
        }

        studentService.deleteStudent(studentId);
        return true;
    }


}

