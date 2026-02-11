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
    public boolean signup(UserData userData) {

        return userService.signup(userData);

    }
}
