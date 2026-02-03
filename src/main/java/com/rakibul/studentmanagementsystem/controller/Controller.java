package com.rakibul.studentmanagementsystem.controller;

import java.util.List;

import com.rakibul.studentmanagementsystem.model.Department;
import com.rakibul.studentmanagementsystem.servicefacade.DepartmentServiceFacade;
import com.rakibul.studentmanagementsystem.servicefacade.StudentServiceFacade;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rakibul.studentmanagementsystem.model.Student;

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
    public String showEditStudentForm(@PathVariable Long id, Model model, HttpSession session) {

        Student student = studentServiceFacade.getStudentById(id); // or studentService if kept

        if (student == null) {
            session.setAttribute("message", "Student not found");
            return "redirect:/students"; //Issue here need to be fixed
        }

        model.addAttribute("student", student);
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "student_edit";
    }





    @PostMapping("/students")
    public String addStudent(@ModelAttribute Student student,
                             @RequestParam("departmentName") String departmentName,
                             HttpSession session) {

        Student saved = studentServiceFacade.createStudent(student, departmentName);

        if (saved != null) {
            session.setAttribute("message", "Student Add Successful");
        } else {
            session.setAttribute("message", "Student Add Failed");
        }

        return "redirect:/students";
    }



    @PostMapping("/students/{id}")
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





    @GetMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        boolean deletedStudent = studentServiceFacade.deleteStudent(id);
        if (deletedStudent) {
            session.setAttribute("message", "Delete Successful");
        } else {
            session.setAttribute("message", "Delete Failed");
        }


        return "redirect:/students";
    }

    //All Department related controller operations

    @PostMapping("/departments/{id}")
    public String updateDepartment(@ModelAttribute Department department,
                                   @PathVariable Long id,
                                   HttpSession session) {

        Department updatedDepartment = departmentServiceFacade.updateDepartment(department,id);

        if (updatedDepartment != null) {
            session.setAttribute("message", "Update Successful");
        } else {
            session.setAttribute("message", "Update Failed");
        }
        //return "redirect:/";
        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}/edit")
    public String showEditDepartmentForm(@PathVariable Long id, Model model, HttpSession session) {

        Department department = departmentServiceFacade.getDepartmentById(id); // or studentService if kept

        if (department == null) {
            session.setAttribute("message", "Department not found");
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
    public String addDepartment(@ModelAttribute Department department, HttpSession session) {
        if (department.getName() != null && !department.getName().isBlank()) {
            departmentServiceFacade.findOrCreateByName(department.getName());
            session.setAttribute("message", "Department added successfully");
        } else {
            session.setAttribute("message", "Department name cannot be empty");
        }
        return "redirect:/departments";
    }






    @GetMapping("/departments/{id}/delete")
    public String deleteDepartment(@PathVariable Long id, HttpSession session) {
        boolean deletedDepartment = departmentServiceFacade.deleteDepartment(id);
        if (deletedDepartment) {
            session.setAttribute("message", "Delete Successful");
        } else {
            session.setAttribute("message", "Delete Failed");
        }


        return "redirect:/departments";
    }


}