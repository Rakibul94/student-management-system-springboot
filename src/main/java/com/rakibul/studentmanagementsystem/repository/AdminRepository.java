package com.rakibul.studentmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rakibul.studentmanagementsystem.model.Admin;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}