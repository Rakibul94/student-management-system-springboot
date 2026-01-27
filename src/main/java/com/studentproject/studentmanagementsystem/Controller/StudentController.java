package com.studentproject.studentmanagementsystem.Controller;

import com.studentproject.studentmanagementsystem.Model.Student;
import com.studentproject.studentmanagementsystem.Repository.StudentRepository;
import com.studentproject.studentmanagementsystem.Service.StudentService;
import com.studentproject.studentmanagementsystem.Service.StudentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String WelcomePage() {
        return "index";
    }

    @GetMapping("/savestudents")
    public String SaveStudent() {
        return "savestudent";
    }

    @GetMapping("/editstudents")
    public String EditStudent() {
        return "editstudent";
    }


    @GetMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("students", studentService.findAllStudents());
        return "students"; // Thymeleaf template name
    }


}