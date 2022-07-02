package com.example.todoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/login", "/*.css", "/static/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(login -> login.loginPage("/login").permitAll());
                httpSecurity.csrf().disable();


        return httpSecurity.build();
    }



}
