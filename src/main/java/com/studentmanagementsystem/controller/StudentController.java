package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.data.StudentData;
import com.studentmanagementsystem.exceptions.ApplicationExceptions;
import com.studentmanagementsystem.servicefacade.DepartmentServiceFacade;
import com.studentmanagementsystem.servicefacade.StudentServiceFacade;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        model.addAttribute("studentList", studentServiceFacade.getAllStudents());
        return "student_list";
    }


    @GetMapping("/new")
    public String showAddStudentForm(Model model) {
        model.addAttribute("studentData", new StudentData());
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "student_add";
    }


    @GetMapping("/{id}/edit")
    public String showEditStudentForm(@PathVariable Long id,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {

        try{
            StudentData studentData = studentServiceFacade.getStudentById(id);
            if (studentData == null) {
                throw new ApplicationExceptions.NotFoundException("Student not found");
            }
        }
        catch(ApplicationExceptions.NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/students";
        }


        model.addAttribute("studentData", studentServiceFacade.getStudentById(id));
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "student_edit";
    }



    @PostMapping
    public String addStudent( @Valid @ModelAttribute StudentData studentData,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model) {

        if(bindingResult.hasErrors()){
            return "student_add";
        }

        try {
           studentServiceFacade.createStudent(studentData);
           redirectAttributes.addFlashAttribute("message", "Student added successfully");
        }catch(RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Student add failed");
        }
        return "redirect:/students";
    }


    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("studentData") StudentData studentData,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "student_edit";
        }

        try{
            studentServiceFacade.updateStudent(studentData);
            redirectAttributes.addFlashAttribute("message", "Update Successful");
        }catch(RuntimeException e){
            redirectAttributes.addFlashAttribute("message", "Update Failed");
        }
        return "redirect:/students";
    }



    @DeleteMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            if(id == null){
                throw new ApplicationExceptions.NotFoundException("Student not found");
            }
            studentServiceFacade.deleteStudentById(id);
            redirectAttributes.addFlashAttribute("message", "Delete Successful");
        } catch (ApplicationExceptions.NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/students";
    }
}
