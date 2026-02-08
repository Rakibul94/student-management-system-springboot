package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.servicefacade.DepartmentServiceFacade;
import com.studentmanagementsystem.servicefacade.StudentServiceFacade;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceFacade studentServiceFacade;
    private final DepartmentServiceFacade departmentServiceFacade;

    public StudentController(StudentServiceFacade studentServiceFacade, DepartmentServiceFacade departmentServiceFacade) {
        this.studentServiceFacade = studentServiceFacade;
        this.departmentServiceFacade = departmentServiceFacade;
    }

    @GetMapping
    public String studentList(Model model) {
        List<Student> list = studentServiceFacade.getAllStudents();
        model.addAttribute("studentList", list);
        return "student_list";
    }


    @GetMapping("/new")
    public String showAddStudentForm(Model model) {
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "student_add";
    }


    @GetMapping("/{id}/edit")
    public String showEditStudentForm(@PathVariable Long id, Model model, HttpSession session
            , RedirectAttributes redirectAttributes) {

        Student student = studentServiceFacade.getStudentById(id); // or studentService if kept

        if (student == null) {

            redirectAttributes.addFlashAttribute("message", "Student not found");
            return "redirect:/students"; //Issue here need to be fixed
        }

        model.addAttribute("student", student);
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "student_edit";
    }





    @PostMapping
    public String addStudent(@ModelAttribute Student student,
                             @RequestParam("departmentId") Long departmentId,
                             HttpSession session, RedirectAttributes redirectAttributes) {

        Student saved = studentServiceFacade.createStudent(student, departmentId);

        if (saved != null) {

            redirectAttributes.addFlashAttribute("message", "Student added successfully");
        } else {

            redirectAttributes.addFlashAttribute("message", "Student add failed");
        }

        return "redirect:/students";
    }




    @PutMapping("/{id}")
    public String updateStudent(@ModelAttribute Student student,
                                @RequestParam("departmentId") Long departmentId,
                                HttpSession session,RedirectAttributes redirectAttributes) {

        Student updatedStudent = studentServiceFacade.updateStudent(student, departmentId);

        if (updatedStudent != null) {

            redirectAttributes.addFlashAttribute("message", "Student updated successfully");
        } else {

            redirectAttributes.addFlashAttribute("message", "Update failed");
        }

        return "redirect:/students";
    }






    @DeleteMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id, HttpSession session,RedirectAttributes redirectAttributes) {
        boolean deletedStudent = studentServiceFacade.deleteStudent(id);
        if (deletedStudent) {

            redirectAttributes.addFlashAttribute("message", "Delete Successful");
        } else {

            redirectAttributes.addFlashAttribute("message", "Delete Failed");
        }


        return "redirect:/students";
    }
}
