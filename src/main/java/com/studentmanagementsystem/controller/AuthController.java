package com.studentmanagementsystem.controller;


import com.studentmanagementsystem.data.UserData;
import com.studentmanagementsystem.servicefacade.UserServiceFacade;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AuthController {


    private final UserServiceFacade userServiceFacade;

    public AuthController(UserServiceFacade userServiceFacade) {

        this.userServiceFacade = userServiceFacade;
    }


    @GetMapping
    public String home() {
        return "home";
    }


    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("userData", new UserData());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserData userData,
                         BindingResult bindingResult,
                         Model model) {

        // Check if validation errors exist
        if (bindingResult.hasErrors()) {
            // Return the signup page and show errors
            model.addAttribute("userData", userData);
            return "signup";
        }

        // Call the service/facade to create user
        userServiceFacade.signup(userData);
        return "redirect:/login";

    }

    @GetMapping("/login")
    public String loginPage(Model model) {

        return "login";
    }
}