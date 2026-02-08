package com.studentmanagementsystem.servicefacade;

import org.springframework.stereotype.Service;

import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.service.DepartmentService;
import com.studentmanagementsystem.service.StudentService;
import org.springframework.transaction.annotation.Transactional;
import com.studentmanagementsystem.data.StudentDTO;

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


//
//    public List<Student> getAllStudents() {
//        return studentService.getAllStudents();
//    }
//
//    public Student getStudentById(Long id) {
//        return studentService.getStudentById(id);
//    }
//
//
//
//    @Transactional
//    public Student updateStudent(Student student, Long departmentId) {
//
//        if (departmentId == null) {
//            return null;
//        }
//
//        Student existingStudent =
//                studentService.getStudentById(student.getId());
//
//        if (existingStudent == null) {
//            return null;
//        }
//
//        Department department =
//                departmentService.getDepartmentById(departmentId);
//
//        if (department == null) {
//            return null;
//        }
//
//        existingStudent.setName(student.getName());
//        existingStudent.setEmail(student.getEmail());
//        existingStudent.setCgpa(student.getCgpa());
//        existingStudent.setProgram(student.getProgram());
//        existingStudent.setDepartment(department);
//
//        return studentService.updateStudent(existingStudent);
//    }
//
//    public Student createStudent(Student student, Long departmentId) {
//
//        if (departmentId == null) {
//            return null;
//        }
//
//
//        // create department if it doesn't exist
//        Department department = departmentService.getDepartmentById(departmentId);
//        if (department == null) {
//            return null; // invalid selection
//        }
//
//        student.setDepartment(department);
//        return studentService.addStudent(student);
//
//    }
//
//
//    @Transactional
//    public boolean deleteStudent(Long studentId) {
//        if (studentId == null) {
//            return false;
//        }
//
//        Student student = studentService.getStudentById(studentId);
//        if (student == null) {
//            return false;
//        }
//
//        studentService.deleteStudent(studentId);
//        return true;
//    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentService.getStudentById(id);
        return student == null ? null : toDTO(student);
    }

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Department department =
                departmentService.getDepartmentById(dto.getDepartmentId());

        if (department == null) return null;

        Student student = toEntity(dto, department);
        Student saved = studentService.addStudent(student);

        return toDTO(saved);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO dto) {
        Student existing = studentService.getStudentById(dto.getId());
        if (existing == null) return null;

        Department department =
                departmentService.getDepartmentById(dto.getDepartmentId());

        if (department == null) return null;

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setCgpa(dto.getCgpa());
        existing.setProgram(dto.getProgram());
        existing.setDepartment(department);

        return toDTO(studentService.updateStudent(existing));
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentService.deleteStudent(id);
    }

    /* ---------- MAPPERS ---------- */

    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setCgpa(student.getCgpa());
        dto.setProgram(student.getProgram());

        if (student.getDepartment() != null) {
            dto.setDepartmentId(student.getDepartment().getId());
            dto.setDepartmentName(student.getDepartment().getName());
        }
        return dto;
    }

    private Student toEntity(StudentDTO dto, Department department) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCgpa(dto.getCgpa());
        student.setProgram(dto.getProgram());
        student.setDepartment(department);
        return student;
    }




}

