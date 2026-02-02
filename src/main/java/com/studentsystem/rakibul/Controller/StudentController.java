package com.studentsystem.rakibul.Controller;

import java.util.List;

import com.studentsystem.rakibul.ServiceFacade.DepartmentServiceFacade;
import com.studentsystem.rakibul.ServiceFacade.StudentServiceFacade;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.studentsystem.rakibul.Model.Student;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceFacade studentServiceFacade;
    private final DepartmentServiceFacade departmentServiceFacade;


    public StudentController(StudentServiceFacade studentServiceFacade, DepartmentServiceFacade departmentServiceFacade) {
        this.studentServiceFacade = studentServiceFacade;
        this.departmentServiceFacade = departmentServiceFacade;
    }


    @GetMapping({ "", "/" })
    public String index(Model model) {
        List<Student> list = studentServiceFacade.getAllStudents();
        model.addAttribute("studentList", list);
        return "index";
    }


    @GetMapping("/new")
    public String showAddStudentForm(Model model) {
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "student_add";
    }


    @GetMapping("/{id}/edit")
    public String showEditStudentForm(@PathVariable Long id, Model model, HttpSession session) {

        Student student = studentServiceFacade.getStudentById(id); // or studentService if kept

        if (student == null) {
            session.setAttribute("message", "Student not found");
            return "redirect:/";
        }

        model.addAttribute("student", student);
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "student_edit";
    }




    @PostMapping
    public String addStudent(@ModelAttribute Student student,
                             @RequestParam("departmentName") String departmentName,
                             HttpSession session) {

        Student saved = studentServiceFacade.createStudent(student, departmentName);

        if (saved != null) {
            session.setAttribute("message", "Student Add Successful");
        } else {
            session.setAttribute("message", "Student Add Failed");
        }

        return "redirect:/students/new";
    }



    @PostMapping("/{id}")
    public String updateStudent(@ModelAttribute Student student,
                                @RequestParam("departmentName") String departmentName,
                                HttpSession session) {

        Student updatedStudent = studentServiceFacade.updateStudent(student, departmentName);

        if (updatedStudent != null) {
            session.setAttribute("message", "Update Successful");
        } else {
            session.setAttribute("message", "Update Failed");
        }
        //return "redirect:/";
        return "redirect:/students";
    }



    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        boolean deletedStudent = studentServiceFacade.deleteStudent(id);
        if (deletedStudent) {
            session.setAttribute("message", "Delete Successful");
        } else {
            session.setAttribute("message", "Delete Failed");
        }


        return "redirect:/students";
    }


}