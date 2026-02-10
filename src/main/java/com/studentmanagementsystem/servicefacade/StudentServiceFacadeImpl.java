package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.mapper.StudentMapper;
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
    private final StudentMapper studentMapper;

    public StudentServiceFacadeImpl(StudentService studentService,
                                    DepartmentService departmentService,
                                    StudentMapper studentMapper) {
        this.studentService = studentService;
        this.departmentService = departmentService;
        this.studentMapper = studentMapper;
    }


    @Override
    public List<StudentData> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(studentMapper::toData)
                .toList();
    }

    @Override
    public StudentData getStudentById(Long id) {
        if (id == null) return null;

        Student student = studentService.getStudentById(id);
        return studentMapper.toData(student);
    }

    @Override
    public StudentData createStudent(StudentData studentData) {

        Department department =
                departmentService.getDepartmentById(studentData.getDepartmentId());

        if (department == null) return null;

        Student student = studentMapper.toEntity(studentData);
        student.setDepartment(department);

        Student savedStudent = studentService.addStudent(student);

        return studentMapper.toData(savedStudent);
    }

    @Override
    public StudentData updateStudent(StudentData studentData) {
        Student existingStudent = studentService.getStudentById(studentData.getId());
        if (existingStudent == null) return null;

        Department department =
                departmentService.getDepartmentById(studentData.getDepartmentId());

        studentMapper.updateEntity(existingStudent, studentData, department);

        return studentMapper.toData(studentService.updateStudent(existingStudent));
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentService.deleteStudent(id);
    }


}

