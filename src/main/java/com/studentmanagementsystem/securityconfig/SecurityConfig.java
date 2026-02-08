package com.studentmanagementsystem.securityconfig;

import com.studentmanagementsystem.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  //This enables Spring Web Security Support
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //Request Authorization
                .csrf(AbstractHttpConfigurer::disable) //csrf is disabled here
                .authorizeHttpRequests(auth -> auth
                        //Anyone can get access to log in,signup and CSS
                        .requestMatchers("/login", "/signup", "/css/**").permitAll()
                        //Only the ones that has ADMIN role can get access
                        .requestMatchers("/**").hasRole("ADMIN")
                        //All requests processing must be from logged-in users
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    //This Encoder hashes password instead of keeping password in plain-text
    //Spring security compares raw password and hashed password during login
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}