package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.data.DepartmentData;
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
    public String updateDepartment(@PathVariable Long id,
                                   @ModelAttribute("department") DepartmentData departmentData,
                                   RedirectAttributes redirectAttributes) {

        if (departmentData == null ||
                departmentData.getName() == null ||
                departmentData.getName().isBlank()) {

            redirectAttributes.addFlashAttribute("message", "Department name cannot be empty");
            return "redirect:/departments/" + id + "/edit";
        }

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
    public String addDepartment(@ModelAttribute DepartmentData departmentData,
                                RedirectAttributes redirectAttributes) {

        if (departmentData == null ||
                departmentData.getName() == null ||
                departmentData.getName().isBlank()) {

            redirectAttributes.addFlashAttribute("message", "Department name cannot be empty");
            return "redirect:/departments/new";
        }

        if (departmentData.getName() != null && !departmentData.getName().isBlank()) {
            departmentServiceFacade.createDepartment(departmentData.getName());

            redirectAttributes.addFlashAttribute("message", "Department added successfully");
        } else {

            redirectAttributes.addFlashAttribute("message", "Department name cannot be empty");
        }
        return "redirect:/departments";

    }



    @DeleteMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable Long id,
                                    RedirectAttributes redirectAttributes) {

        try {
            departmentServiceFacade.deleteDepartment(id);
            redirectAttributes.addFlashAttribute("message", "Delete Successful");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Delete Failed");
        }

        return "redirect:/departments";


    }
}
