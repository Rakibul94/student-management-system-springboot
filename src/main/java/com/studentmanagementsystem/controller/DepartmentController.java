package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.data.DepartmentData;
import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.servicefacade.DepartmentServiceFacade;
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
    public String updateDepartment(@ModelAttribute("department") DepartmentData departmentData,
                                   @PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {

        DepartmentData updatedDepartment = departmentServiceFacade.updateDepartment(departmentData);

        if (updatedDepartment != null) {

            redirectAttributes.addFlashAttribute("message", "Update Successful");
        } else {

            redirectAttributes.addFlashAttribute("message", "Update Failed");
        }

        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String showEditDepartmentForm(@PathVariable Long id,
                                         Model model,
                                         RedirectAttributes redirectAttributes) {

        DepartmentData department = departmentServiceFacade.getDepartmentById(id);

        if (department == null) {

            redirectAttributes.addFlashAttribute("message", "Department not found");
            return "redirect:/departments";
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
    public String addDepartment(@ModelAttribute Department department,
                                RedirectAttributes redirectAttributes) {

        if (department.getName() != null && !department.getName().isBlank()) {
            departmentServiceFacade.createDepartment(department.getName());

            redirectAttributes.addFlashAttribute("message", "Department added successfully");
        } else {

            redirectAttributes.addFlashAttribute("message", "Department name cannot be empty");
        }
        return "redirect:/departments";

    }



    @DeleteMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable Long id,
                                    RedirectAttributes redirectAttributes) {
        boolean deletedDepartment = departmentServiceFacade.deleteDepartment(id);
        if (deletedDepartment) {

            redirectAttributes.addFlashAttribute("message", "Delete Successful");
        } else {

            redirectAttributes.addFlashAttribute("message", "Delete Failed");
        }

        return "redirect:/departments";

    }
}
