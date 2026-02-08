package com.studentmanagementsystem.controller;


import com.studentmanagementsystem.data.UserDTO;
import com.studentmanagementsystem.servicefacade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {


    private final UserFacade userFacade;

    public AuthController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }


    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         UserDTO userDTO) {

        userFacade.signup(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {

        return "login";
    }
}