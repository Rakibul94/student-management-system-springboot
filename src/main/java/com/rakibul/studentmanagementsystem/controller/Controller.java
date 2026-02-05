package com.rakibul.studentmanagementsystem.controller;

import java.util.List;

import com.rakibul.studentmanagementsystem.model.Department;
import com.rakibul.studentmanagementsystem.servicefacade.DepartmentServiceFacade;
import com.rakibul.studentmanagementsystem.servicefacade.StudentServiceFacade;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rakibul.studentmanagementsystem.model.Student;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    private final StudentServiceFacade studentServiceFacade;
    private final DepartmentServiceFacade departmentServiceFacade;


    public Controller(StudentServiceFacade studentServiceFacade, DepartmentServiceFacade departmentServiceFacade) {
        this.studentServiceFacade = studentServiceFacade;
        this.departmentServiceFacade = departmentServiceFacade;
    }


    //All Student related controller operations

    @GetMapping({ "", "/" })
    public String home(Model model) {
        return "home";
    }




    @GetMapping({"/students" })
    public String studentList(Model model) {
        List<Student> list = studentServiceFacade.getAllStudents();
        model.addAttribute("studentList", list);
        return "student_list";
    }


    @GetMapping("/students/new")
    public String showAddStudentForm(Model model) {
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "student_add";
    }


    @GetMapping("/students/{id}/edit")
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





    @PostMapping("/students")
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



    @PostMapping("/students/{id}")
    public String updateStudent(@ModelAttribute Student student,
                                @RequestParam("departmentId") Long departmentId,
                                HttpSession session,RedirectAttributes redirectAttributes) {

        Student updatedStudent = studentServiceFacade.updateStudent(student, departmentId);

        if (updatedStudent != null) {

            redirectAttributes.addFlashAttribute("message", "Student updated successfully");
        } else {

            redirectAttributes.addFlashAttribute("message", "Update failed");
        }
        //return "redirect:/";
        return "redirect:/students";
    }





    @GetMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable Long id, HttpSession session,RedirectAttributes redirectAttributes) {
        boolean deletedStudent = studentServiceFacade.deleteStudent(id);
        if (deletedStudent) {

            redirectAttributes.addFlashAttribute("message", "Delete Successful");
        } else {

            redirectAttributes.addFlashAttribute("message", "Delete Failed");
        }


        return "redirect:/students";
    }

    //All Department related controller operations

    @PostMapping("/departments/{id}")
    public String updateDepartment(@ModelAttribute Department department,
                                   @PathVariable Long id,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {

        Department updatedDepartment = departmentServiceFacade.updateDepartment(department,id);

        if (updatedDepartment != null) {

            redirectAttributes.addFlashAttribute("message", "Update Successful");
        } else {

            redirectAttributes.addFlashAttribute("message", "Update Failed");
        }
        //return "redirect:/";
        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}/edit")
    public String showEditDepartmentForm(@PathVariable Long id, Model model, HttpSession session
                                         ,RedirectAttributes redirectAttributes) {

        Department department = departmentServiceFacade.getDepartmentById(id); // or studentService if kept

        if (department == null) {
            //session.setAttribute("message", "Department not found");
            redirectAttributes.addFlashAttribute("message", "Department not found");
            return "redirect:/departments"; //Issue here need to be fixed
        }

        model.addAttribute("department", department);
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());

        return "department_edit";
    }


    @GetMapping("/departments/new")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "department_add";
    }

    @GetMapping("/departments")
    public String departmentList(Model model) {
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "department_list";
    }

    @PostMapping("/departments")
    public String addDepartment(@ModelAttribute Department department, HttpSession session
                                ,RedirectAttributes redirectAttributes) {
        if (department.getName() != null && !department.getName().isBlank()) {
            departmentServiceFacade.findOrCreateByName(department.getName());

            redirectAttributes.addFlashAttribute("message", "Department added successfully");
        } else {

            redirectAttributes.addFlashAttribute("message", "Department name cannot be empty");
        }
        return "redirect:/departments";
    }






    @GetMapping("/departments/{id}/delete")
    public String deleteDepartment(@PathVariable Long id, HttpSession session
            ,RedirectAttributes redirectAttributes) {
        boolean deletedDepartment = departmentServiceFacade.deleteDepartment(id);
        if (deletedDepartment) {

            redirectAttributes.addFlashAttribute("message", "Delete Successful");
        } else {

            redirectAttributes.addFlashAttribute("message", "Delete Failed");
        }


        return "redirect:/departments";
    }


}