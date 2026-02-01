package com.studentsystem.rakibul.Controller;

import java.util.List;

import com.studentsystem.rakibul.Facade.StudentFacade;
import com.studentsystem.rakibul.Service.DepartmentService;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.studentsystem.rakibul.Model.Student;
import com.studentsystem.rakibul.Service.StudentService;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final StudentFacade studentFacade;
    private final DepartmentService departmentService;


    public StudentController(StudentService studentService,
                             StudentFacade studentFacade,
                             DepartmentService departmentService
                             ) {

        this.studentService = studentService;
        this.studentFacade = studentFacade;
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Student> list = studentService.getAllStudents();
        model.addAttribute("studentList", list);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

//    @GetMapping("/loadaddstudents")
//    public String loadAddStudent() {
//        return "student_add";
//    }

    @GetMapping("/loadaddstudents")
    public String loadAddStudent(Model model) {
        model.addAttribute("departmentList", departmentService.getAllDepartments());
        return "student_add";
    }


//    @GetMapping("/editstudents/{id}")
//    public String EditStudent(@PathVariable Long id, Model model, HttpSession session) {
//        Student student = studentService.getStudentById(id);
//        if (student == null) {
//            session.setAttribute("message", "Student not found");
//            return "redirect:/";
//        }
//
//        model.addAttribute("student", student);
//        return "student_edit";
//    }

    @GetMapping("/editstudents/{id}")
    public String EditStudent(@PathVariable Long id, Model model, HttpSession session) {

        Student student = studentService.getStudentById(id); // or studentService if kept

        if (student == null) {
            session.setAttribute("message", "Student not found");
            return "redirect:/";
        }

        model.addAttribute("student", student);
        model.addAttribute("departmentList", departmentService.getAllDepartments());
        return "student_edit";
    }

//    @PostMapping("/addstudents")
//    public String AddStudent(@ModelAttribute Student student, HttpSession session) {
//        // System.out.println(s);
//
//        Student newstudent = studentService.addStudent(student);
//
//        if (newstudent != null) {
//            session.setAttribute("message", "Student Add Successful");
//        } else {
//            session.setAttribute("message", "Something went wrong");
//        }
//        return "redirect:/loadaddstudents";
//    }

    @PostMapping("/addstudents")
    public String AddStudent(@ModelAttribute Student student,
                             @RequestParam("departmentName") String departmentName,
                             HttpSession session) {

        Student saved = studentFacade.createStudent(student, departmentName);

        if (saved != null) {
            session.setAttribute("message", "Student Add Successful");
        } else {
            session.setAttribute("message", "Department name is required");
        }
        return "redirect:/loadaddstudents";
    }

//    @PostMapping("/updatestudents")
//    public String UpdateStudent(@ModelAttribute Student student, HttpSession session) {
//        // System.out.println(s);
//
//        Student updatestudent = studentService.updateStudent(student);
//
//        if (updatestudent != null) {
//            session.setAttribute("message", "Update Successful");
//        } else {
//            session.setAttribute("message", "Something went wrong");
//        }
//        return "redirect:/";
//    }

    @PostMapping("/updatestudents")
    public String UpdateStudent(@ModelAttribute Student student,
                                @RequestParam("departmentName") String departmentName,
                                HttpSession session) {

        Student updatedStudent = studentFacade.updateStudent(student, departmentName);

        if (updatedStudent != null) {
            session.setAttribute("message", "Update Successful");
        } else {
            session.setAttribute("message", "Update Failed");
        }
        return "redirect:/";
    }


    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        boolean deletedStudent = studentService.deleteStudent(id);
        if (deletedStudent) {
            session.setAttribute("message", "Delete Successful");
        } else {
            session.setAttribute("message", "Something went wrong");
        }

        return "redirect:/";
    }

}