package com.studentmanagementsystem.service;


import com.studentmanagementsystem.data.UserData;
import com.studentmanagementsystem.exceptions.ApplicationExceptions;
import com.studentmanagementsystem.model.User;
import com.studentmanagementsystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signup(UserData userData) {

        //Check for username already exists
        if (userRepository.findByUsername(userData.getUsername()).isPresent()) {
           throw new ApplicationExceptions.UserAlreadyExistsException("Username already exists"); //It is handled by
            //AuthController
        }
        //This username is stored in DB
        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setRole("ROLE_ADMIN"); //Admin is the only user at the moment for this webpage

        userRepository.save(user);
    }
}
