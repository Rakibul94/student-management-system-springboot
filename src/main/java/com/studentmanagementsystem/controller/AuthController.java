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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/signup")
    public String signupPage(Model model) {
        //When signup page is loaded an empty UserData object is created
        //Thymeleaf uses this to render the form with fields bound to this object
        model.addAttribute("userData", new UserData());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserData userData,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        // Check if validation errors exist
        //bindingResults successfully binds form data to java object
        //If any valid rules are violated then bindingResult already has errors stored
        //and hence error message is shown instead of throwing exception
        if (bindingResult.hasErrors()) {
            // Return the signup page and show errors
            model.addAttribute("userData", userData);
            return "signup";
        }

        //Using Redirect attribute
        try{
             userServiceFacade.signup(userData);
         }
         catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Username already exists"
            );
            model.addAttribute("userData", userData);
            return "redirect:/signup";
        }

        //Using Binding Result
//         try{
//             userServiceFacade.signup(userData);
//         }
//         catch (RuntimeException e){
//            // This adds a **field error** to 'username' in BindingResult
//            bindingResult.rejectValue(
//                    "username",       // field name in UserData
//                    "error.userData", // error code
//                    "Username already exists" // Error message to show
//            );
//
//            model.addAttribute("userData", userData);
//            return "signup";
//        }

        redirectAttributes.addFlashAttribute("message", "Signup successful!");
        return "redirect:/login";

    }

}