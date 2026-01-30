package com.studentsystem.rakibul.Controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.studentsystem.rakibul.Model.Student;
import com.studentsystem.rakibul.Service.StudentService;

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
    public String EditStudent(@PathVariable Long id, Model model, HttpSession session) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            session.setAttribute("message", "Student not found");
            return "redirect:/";
        }

        model.addAttribute("student", student);
        return "student_edit";
    }

    @PostMapping("/addstudents")
    public String AddStudent(@ModelAttribute Student student, HttpSession session) {
        // System.out.println(s);

        Student newstudent = studentService.addStudent(student);

        if (newstudent != null) {
            session.setAttribute("message", "Student Add Successful");
        } else {
            session.setAttribute("message", "Something went wrong");
        }
        return "redirect:/loadaddstudents";
    }

    @PostMapping("/updatestudents")
    public String UpdateStudent(@ModelAttribute Student student, HttpSession session) {
        // System.out.println(s);

        Student updatestudent = studentService.updateStudent(student);

        if (updatestudent != null) {
            session.setAttribute("message", "Update Successful");
        } else {
            session.setAttribute("message", "Something went wrong");
        }
        return "redirect:/";
    }

    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        boolean f = studentService.deleteStudent(id);
        if (f) {
            session.setAttribute("message", "Delete Successful");
        } else {
            session.setAttribute("message", "Something went wrong");
        }

        return "redirect:/";
    }

}