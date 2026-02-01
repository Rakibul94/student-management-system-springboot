package com.studentsystem.rakibul.SecurityConfig;

import com.studentsystem.rakibul.Model.Admin;
import com.studentsystem.rakibul.Repository.AdminRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


//A default admin is created here so that it can be used to access without admin form registration
//This default admin is acting as a superuser

@Component
public class AdminDataLoader implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminDataLoader(AdminRepository adminRepository,
                           PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String @NonNull ... args) {

        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            admin.setEnabled(true);

            adminRepository.save(admin);

            System.out.println("Default admin created");
        }
    }
}