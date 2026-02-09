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
                .map(this::toData)
                .toList();
    }

    @Override
    public StudentData getStudentById(Long id) {
        Student student = studentService.getStudentById(id);
        return student == null ? null : toData(student);
    }

    @Override
    public StudentData createStudent(StudentData studentData) {
        Department department =
                departmentService.getDepartmentById(studentData.getDepartmentId());

        if (department == null) return null;

        Student student = toEntity(studentData, department);
        Student savedStudent = studentService.addStudent(student);

        return toData(savedStudent);
    }

    @Override
    public StudentData updateStudent(StudentData studentData) {
        Student existingStudent = studentService.getStudentById(studentData.getId());
        if (existingStudent == null) return null;

        Department department =
                departmentService.getDepartmentById(studentData.getDepartmentId());

        if (department == null) return null;

        existingStudent.setName(studentData.getName());
        existingStudent.setEmail(studentData.getEmail());
        existingStudent.setCgpa(studentData.getCgpa());
        existingStudent.setProgram(studentData.getProgram());
        existingStudent.setDepartment(department);

        return toData(studentService.updateStudent(existingStudent));
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentService.deleteStudent(id);
    }

    /* ---------- MAPPERS ---------- */

    private StudentData toData(Student student) {
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

