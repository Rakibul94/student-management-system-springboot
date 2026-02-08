package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.UserDTO;
import com.studentmanagementsystem.service.CustomUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeImpl implements UserFacade{
    private final CustomUserDetailsService userService;

    public UserFacadeImpl(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    public void signup(UserDTO dto) {
        // You can add extra logic here, e.g., logging, auditing, events
        userService.signup(dto);
    }
}
