package com.studentsystem.rakibul.Facade;

import org.springframework.stereotype.Service;

import com.studentsystem.rakibul.Model.Department;
import com.studentsystem.rakibul.Model.Student;
import com.studentsystem.rakibul.Service.DepartmentService;
import com.studentsystem.rakibul.Service.StudentService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentFacade {
    private final StudentService studentService;
    private final DepartmentService departmentService;

    public StudentFacade(StudentService studentService,
                         DepartmentService departmentService) {
        this.studentService = studentService;
        this.departmentService = departmentService;
    }

//    // Use case: create student with department
//    public Student createStudent(Student student, Long departmentId) {
//
//        Department department = departmentService.getDepartmentById(departmentId);
//
//        if (department == null) {
//            return null;
//        }
//
//        student.setDepartment(department);
//        return studentService.addStudent(student);
//    }
//
//    // Use case: update student with department
//    public Student updateStudent(Student student, Long departmentId) {
//
//        Department department = departmentService.getDepartmentById(departmentId);
//
//        if (department == null) {
//            return null;
//        }
//
//        student.setDepartment(department);
//        return studentService.updateStudent(student);
//    }

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





}

