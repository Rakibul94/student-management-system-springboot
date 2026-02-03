package com.rakibul.studentmanagementsystem.servicefacade;

import org.springframework.stereotype.Service;

import com.rakibul.studentmanagementsystem.model.Department;
import com.rakibul.studentmanagementsystem.model.Student;
import com.rakibul.studentmanagementsystem.service.DepartmentService;
import com.rakibul.studentmanagementsystem.service.StudentService;
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

    public Student createStudent(Student student, String departmentName) {

        if (departmentName == null || departmentName.isBlank()) {
            return null;
        }

        Department department =
                departmentService.findByName(departmentName);

        // create department if it doesn't exist
        if (department == null) {
            department = new Department();
            department.setName(departmentName);
            department = departmentService.save(department);
        }

        student.setDepartment(department);
        return studentService.addStudent(student);
    }
    @Transactional
    public Student updateStudent(Student student, String departmentName) {

        if (departmentName == null || departmentName.trim().isEmpty()) {
            return null;
        }

        // fetch existing student
        Student existingStudent = studentService.getStudentById(student.getId());
        if (existingStudent == null) {
            return null;
        }

        // find or create department
        Department department = departmentService.findByName(departmentName);
        if (department == null) {
            department = new Department();
            department.setName(departmentName);
            department = departmentService.save(department);
        }

        // update fields
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCgpa(student.getCgpa());
        existingStudent.setProgram(student.getProgram());
        existingStudent.setDepartment(department);

        return studentService.updateStudent(existingStudent);
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

