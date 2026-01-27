package com.studentproject.studentmanagementsystem.Controller;

import java.util.List;

import com.studentproject.studentmanagementsystem.Model.Student;
import com.studentproject.studentmanagementsystem.Repository.StudentRepository;
import com.studentproject.studentmanagementsystem.Service.StudentService;
import com.studentproject.studentmanagementsystem.Service.StudentServiceImpl;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Student> list = studentService.getAllStudents();
        model.addAttribute("studentList", list);
        return "index";
    }

    @GetMapping("/loadaddstudents")
    public String loadAddStudent() {
        return "student_add";
    }

    @GetMapping("/editstudents/{id}")
    public String EditStudent(@PathVariable int id, Model model) {
        Student s = studentService.getStudentById(id);
        model.addAttribute("s", s);
        return "student_edit";
    }

    @PostMapping("/addstudents")
    public String AddStudent(@ModelAttribute Student s, HttpSession session) {
        // System.out.println(s);

        Student newstudent = studentService.addStudent(s);

        if (newstudent != null) {
            session.setAttribute("msg", "Student Added Successfully");
        } else {
            session.setAttribute("msg", "Something went wrong");
        }
        return "redirect:/loadaddstudents";
    }

    @PostMapping("/updatestudents")
    public String UpdateStudent(@ModelAttribute Student s, HttpSession session) {
        // System.out.println(s);

        Student updatestudent = studentService.addStudent(s);

        if (updatestudent != null) {
            session.setAttribute("msg", "Update Successful");
        } else {
            session.setAttribute("msg", "Something went wrong");
        }
        return "redirect:/";
    }

    @GetMapping("/deletestudent/{id}")
    public String DeleteStudent(@PathVariable int id, HttpSession session) {
        boolean f = studentService.deleteStudent(id);
        if (f) {
            session.setAttribute("msg", "Delete Successful");
        } else {
            session.setAttribute("msg", "Something went wrong");
        }

        return "redirect:/";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; // Thymeleaf template name
    }

}