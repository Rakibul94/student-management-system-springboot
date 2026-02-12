package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.service.DepartmentService;
import com.studentmanagementsystem.service.StudentService;
import com.studentmanagementsystem.data.StudentData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                // This guarantees the result is a mutable ArrayList
                .collect(Collectors.toCollection(ArrayList::new));
    }

//    @Override
//    public List<StudentData> getAllStudents() {
//        List<Student> studentList = studentService.getAllStudents();
//        List<StudentData> studentDataList = new ArrayList<>();
//
//        for (Student student : studentList) {
//            studentDataList.add(studentMapper.toData(student));
//        }
//
//        return studentDataList;
//    }

    @Override
    public StudentData getStudentById(Long id) {
        Student student = studentService.getStudentById(id);
        return studentMapper.toData(student);
    }

    @Override
    public StudentData createStudent(StudentData studentData) {
        Department department =
                departmentService.getDepartmentById(studentData.getDepartmentId());

        Student student = studentMapper.toEntity(studentData);
        student.setDepartment(department);

        Student savedStudent = studentService.saveStudent(student);

        return studentMapper.toData(savedStudent);
    }

    @Override
    public StudentData updateStudent(StudentData studentData) {
        Student existingStudent = studentService.getStudentById(studentData.getId());

        Department department =
                departmentService.getDepartmentById(studentData.getDepartmentId());

        studentMapper.updateEntity(existingStudent, studentData, department);

        return studentMapper.toData(studentService.saveStudent(existingStudent));
    }

    @Override
    public void deleteStudentById(Long id) {
         studentService.deleteStudentById(id);
    }


}

