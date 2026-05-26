package com.irenejoy15.springboot.todos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.irenejoy15.springboot.todos.entity.Authority;
import com.irenejoy15.springboot.todos.entity.User;
import com.irenejoy15.springboot.todos.repository.UserRepository;
import com.irenejoy15.springboot.todos.request.RegisterRequest;

import jakarta.transaction.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    // B.1 - Create Annotation @Service on the AuthenticationServiceImpl class to indicate that it's a service component in the Spring context. This allows Spring to manage the lifecycle of this class and inject it where needed.
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // B.2 - Create a constructor for AuthenticationServiceImpl that takes UserRepository and PasswordEncoder as parameters and assigns them to the respective fields. This allows Spring to inject the necessary dependencies when creating an instance of AuthenticationServiceImpl.
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    @Transactional
    public void register(RegisterRequest input) throws Exception {

        if(emailExists(input.getEmail())) {
            throw new Exception("Email already exists");
        }

        User user = buildNewUser(input);
        userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User buildNewUser(RegisterRequest input) {
        User user = new User();
        user.setId(0);
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setAuthorities(initialAuthority());
        return user;
    }

    private List<Authority> initialAuthority(){
        boolean isFirstUser = userRepository.count() == 0;
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_EMPLOYEE"));
        if(isFirstUser) {
            authorities.add(new Authority("ROLE_ADMIN"));
        }
        return authorities;
    }

}
