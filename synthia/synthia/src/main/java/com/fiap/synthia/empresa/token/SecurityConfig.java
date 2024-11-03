package com.fiap.synthia.empresa.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
public SecurityFilterChain config(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable());

    http.authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.GET, "/users/register", "/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users", "/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/home", "/dados/**").permitAll()
                    .anyRequest().permitAll()
    )
    .formLogin(form -> form
        .loginPage("/login")
        .defaultSuccessUrl("/home", true)
        .permitAll()
    )
    .logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
        .permitAll()
    );

    return http.build();
}

}