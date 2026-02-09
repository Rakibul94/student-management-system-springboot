package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.UserData;
import com.studentmanagementsystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {
    private final UserService userService;

    public UserServiceFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void signup(UserData userData) {
        // You can add extra logic here, e.g., logging, auditing, events
        userService.signup(userData);
    }
}
