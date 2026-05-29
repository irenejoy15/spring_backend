package com.irenejoy15.springboot.todos.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.irenejoy15.springboot.todos.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

// A.1 - Create a SecurityConfig class and annotate it with @Configuration and @EnableWebSecurity. This class will contain the security configuration for the application, including JWT authentication and authorization settings.
// Declares Multiple Beans and Configurations
@Configuration
// Enables Spring Security's web security support and provides the Spring MVC integration. This annotation is crucial for setting up the security configuration in a Spring Boot application.
@EnableWebSecurity
public class SecurityConfig {
    // A.2 Create two private final fields: UserRepository userRepository and JwtAuthenticationFilter jwtAuthFilter. These will be injected via the constructor to be used in the security configuration.
    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthFilter;

    // A.3 - Create a constructor for SecurityConfig that takes UserRepository and JwtAuthenticationFilter as parameters and assigns them to the respective fields. This allows Spring to inject the necessary dependencies when creating an instance of SecurityConfig. by pressing Ctrl + . , you can import the required classes automatically.
    public SecurityConfig(UserRepository userRepository, JwtAuthenticationFilter jwtAuthFilter) {
        this.userRepository = userRepository;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    // A.4 Creation Beans
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Password Using Bcrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // Core Interface for spring verifying user credentials and managing authentication. By defining this bean, we can use it in our authentication logic to authenticate users based on their credentials.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request,response, ex)->{
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setHeader("WWW-Authenticate", "");
            response.getWriter().write("{\"error\": \"Unauthorized access\"}");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> 
            configurer
                .requestMatchers("/api/auth/**","/swagger-ui/**","/v3/api-docs/**","/swagger-resources/**",
                    "/webjars/**","/docs").permitAll()
                    // 4.1 - Update the securityFilterChain method to configure the HttpSecurity object to allow unauthenticated access to the /api/auth/** endpoints (for registration and login) and require authentication for all other endpoints. This can be done using the authorizeHttpRequests method and specifying the appropriate matchers and access rules.
                .anyRequest().authenticated()
        );

        http.csrf(csrf->csrf.disable());

        http.exceptionHandling(exceptionHandling -> 
            exceptionHandling.authenticationEntryPoint(authenticationEntryPoint()));
        
        http.sessionManagement(session->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
