package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.UserData;
import com.studentmanagementsystem.service.CustomUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {
    private final CustomUserDetailsService userService;

    public UserServiceFacadeImpl(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    public void signup(UserData userData) {
        // You can add extra logic here, e.g., logging, auditing, events
        userService.signup(userData);
    }
}
