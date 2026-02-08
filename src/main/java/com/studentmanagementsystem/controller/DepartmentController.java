package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.servicefacade.DepartmentServiceFacade;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentServiceFacade departmentServiceFacade;

    public DepartmentController(DepartmentServiceFacade departmentServiceFacade) {
        this.departmentServiceFacade = departmentServiceFacade;
    }

    @PutMapping("/{id}")
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

        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String showEditDepartmentForm(@PathVariable Long id, Model model, HttpSession session
            , RedirectAttributes redirectAttributes) {

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


    @GetMapping("/new")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "department_add";
    }

    @GetMapping
    public String departmentList(Model model) {
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "department_list";
    }

    @PostMapping
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



    @DeleteMapping("/{id}/delete")
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
