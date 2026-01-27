package com.studentproject.studentmanagementsystem.Config;

import com.studentproject.studentmanagementsystem.Model.Student;
import com.studentproject.studentmanagementsystem.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(StudentRepository repo) {
        return args -> {
            repo.save(new Student("Rakibul", 3.8, "rakibulislam12@gmail.com", "CS"));
            repo.save(new Student("Wasif", 3.96, "wasif@gmail.com", "BBS"));
            repo.save(new Student("Marufa", 3.91, "marufa@gmail.com", "EEE"));
            repo.save(new Student("Adiba", 3.73, "adiba@gmail.com", "ARC"));
        };
    }
}