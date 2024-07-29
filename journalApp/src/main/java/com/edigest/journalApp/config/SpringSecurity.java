package com.edigest.journalApp.config; 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.edigest.journalApp.service.UserDetailsServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity

public class SpringSecurity {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;




    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/journal/**","/user/**").authenticated()
                        .requestMatchers("/signin").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .formLogin(formlogin->formlogin.loginPage("/signin")
                        .loginProcessingUrl("/login").permitAll())
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

   
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
