package com.studentmanagementsystem.servicefacade;

import org.springframework.stereotype.Service;

import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.service.DepartmentService;
import com.studentmanagementsystem.service.StudentService;
import com.studentmanagementsystem.data.StudentData;

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


    @Override
    public List<StudentData> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public StudentData getStudentById(Long id) {
        Student student = studentService.getStudentById(id);
        return student == null ? null : toDTO(student);
    }

    @Override
    public StudentData createStudent(StudentData studentData) {
        Department department =
                departmentService.getDepartmentById(studentData.getDepartmentId());

        if (department == null) return null;

        Student student = toEntity(studentData, department);
        Student saved = studentService.addStudent(student);

        return toDTO(saved);
    }

    @Override
    public StudentData updateStudent(StudentData studentData) {
        Student existing = studentService.getStudentById(studentData.getId());
        if (existing == null) return null;

        Department department =
                departmentService.getDepartmentById(studentData.getDepartmentId());

        if (department == null) return null;

        existing.setName(studentData.getName());
        existing.setEmail(studentData.getEmail());
        existing.setCgpa(studentData.getCgpa());
        existing.setProgram(studentData.getProgram());
        existing.setDepartment(department);

        return toDTO(studentService.updateStudent(existing));
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentService.deleteStudent(id);
    }

    /* ---------- MAPPERS ---------- */

    private StudentData toDTO(Student student) {
        StudentData studentData = new StudentData();
        studentData.setId(student.getId());
        studentData.setName(student.getName());
        studentData.setEmail(student.getEmail());
        studentData.setCgpa(student.getCgpa());
        studentData.setProgram(student.getProgram());

        if (student.getDepartment() != null) {
            studentData.setDepartmentId(student.getDepartment().getId());
            studentData.setDepartmentName(student.getDepartment().getName());
        }
        return studentData;
    }

    private Student toEntity(StudentData studentData, Department department) {
        Student student = new Student();
        student.setName(studentData.getName());
        student.setEmail(studentData.getEmail());
        student.setCgpa(studentData.getCgpa());
        student.setProgram(studentData.getProgram());
        student.setDepartment(department);
        return student;
    }




}

