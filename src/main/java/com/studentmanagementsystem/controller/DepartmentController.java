package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.data.DepartmentData;
import com.studentmanagementsystem.exceptions.ApplicationExceptions;
import com.studentmanagementsystem.servicefacade.DepartmentServiceFacade;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
                                   @Valid @ModelAttribute("departmentData") DepartmentData departmentData,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "department_edit";
        }

        try{
            departmentServiceFacade.updateDepartment(departmentData);
            redirectAttributes.addFlashAttribute("message", "Update Successful");
        }
        catch(RuntimeException e){
            redirectAttributes.addFlashAttribute("message", "Update Failed");
        }
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String showEditDepartmentForm(@PathVariable Long id,
                                         Model model,
                                         RedirectAttributes redirectAttributes) {

        try {
            DepartmentData departmentData = departmentServiceFacade.getDepartmentById(id);
            if (departmentData == null) {
                throw new ApplicationExceptions.NotFoundException("Department not found");
            }

            model.addAttribute("departmentData", departmentData);
            return "department_edit";

        } catch (ApplicationExceptions.NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/departments";
        }
    }


    @GetMapping("/new")
    public String showAddDepartmentForm(Model model) {
        //This empty object is created for thymeleaf to render the form field to this
        //object
        model.addAttribute("departmentData", new DepartmentData());
        return "department_add";
    }

    @GetMapping
    public String departmentList(Model model) {
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "department_list";
    }

    @PostMapping
    public String addDepartment(@Valid @ModelAttribute DepartmentData departmentData,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        //If Input validity is violated bindingResult shows error
        if(bindingResult.hasErrors()){
            return "department_add";
        }

        try{
            departmentServiceFacade.createDepartment(departmentData);
            redirectAttributes.addFlashAttribute("message", "Department added successfully");
        }catch(RuntimeException e){
            redirectAttributes.addFlashAttribute("message", "Department add failed");
        }

        return "redirect:/departments";

    }

    @DeleteMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable Long id,
                                    RedirectAttributes redirectAttributes) {

        try {
            if(id == null){
                throw new ApplicationExceptions.NotFoundException("Department not found");
            }
            departmentServiceFacade.deleteDepartmentById(id);
            redirectAttributes.addFlashAttribute("message", "Delete Successful");
        } catch (ApplicationExceptions.NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/departments";

    }
}
