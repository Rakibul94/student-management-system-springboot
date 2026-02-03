package com.rakibul.studentmanagementsystem.service;

import com.rakibul.studentmanagementsystem.model.Admin;
import com.rakibul.studentmanagementsystem.repository.AdminRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Invalid username or password"));

        // 🔐 IMPORTANT: enabled check
        if (!admin.isEnabled()) {
            throw new DisabledException("Admin account is disabled");
        }

        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .roles("ADMIN")   // 👈 keep explicit, safer
                .build();
    }
}