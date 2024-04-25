package com.example.previoussecurityassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for simplicity, consider enabling in production
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/product","/product/").permitAll() //permit everyone to access /
                        .requestMatchers(HttpMethod.GET, "/product/insert")
                        .hasRole("SuperAdmin")
                        .requestMatchers(HttpMethod.GET, "/product/delete/")
                        .hasAnyRole("SuperAdmin","Admin")
                        .anyRequest()
                        .authenticated())
                .formLogin(formLogin ->
                        formLogin
                                .defaultSuccessUrl("/product/")
                                .permitAll()
                );
        return http.build();
    }
// .requestMatchers(request -> request.getMethod().equals("GET") && request.getServletPath().contains("t"))
// .hasAnyRole("EMPLOYEE")

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails superAdmin =  User.withUsername("bob")
                .password(passwordEncoder.encode("pass"))
                .roles("SuperAdmin")
                .build();
        UserDetails admin =  User.withUsername("tom")
                .password(passwordEncoder.encode("pass"))
                .roles("Admin")
                .build();
        UserDetails user =  User.withUsername("mary")
                .password(passwordEncoder.encode("pass"))
                .roles("User")
                .build();
        return new InMemoryUserDetailsManager(superAdmin, admin, user);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
