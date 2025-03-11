package com.getsuga.SpringSec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
        private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;
      @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.csrf(customizer -> customizer.disable());
            http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
            http.formLogin(customizer -> customizer.disable());
            http.httpBasic(Customizer.withDefaults());
            http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
        @Bean
        public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
            provider.setUserDetailsService(userDetailsService);
            return provider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
            return config.getAuthenticationManager();
        }
        /*
            How JWT Filter and Util Complete Each Other:
            JwtFilter relies on JwtUtil to do the heavy lifting (validate/extract).
            JwtUtil provides reusable logic that JwtFilter (and other parts of the app) can call.
            Together, they handle JWT authentication: JwtUtil processes the token, and JwtFilter applies it to the request.
         */
}
