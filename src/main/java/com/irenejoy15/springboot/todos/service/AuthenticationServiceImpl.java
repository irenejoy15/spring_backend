package com.irenejoy15.springboot.todos.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.irenejoy15.springboot.todos.entity.Authority;
import com.irenejoy15.springboot.todos.entity.User;
import com.irenejoy15.springboot.todos.repository.UserRepository;
import com.irenejoy15.springboot.todos.request.AuthenticationRequest;
import com.irenejoy15.springboot.todos.request.RegisterRequest;
import com.irenejoy15.springboot.todos.response.AuthenticationResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    // B.1 - Create Annotation @Service on the AuthenticationServiceImpl class to indicate that it's a service component in the Spring context. This allows Spring to manage the lifecycle of this class and inject it where needed.
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //D.2 - Create a private final field of type AuthenticationManager in the AuthenticationServiceImpl class. This will be used to authenticate user credentials during the login process.
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // B.2 - Create a constructor for AuthenticationServiceImpl that takes UserRepository and PasswordEncoder as parameters and assigns them to the respective fields. This allows Spring to inject the necessary dependencies when creating an instance of AuthenticationServiceImpl.
    // D.3 - Update the constructor of AuthenticationServiceImpl to also include AuthenticationManager as a parameter and assign it to the authenticationManager field. This ensures that the authentication manager is available for use in the authenticate method when validating user credentials.
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

    // D.1 - Implement the authenticate method in the AuthenticationServiceImpl class. This method will take an AuthenticationRequest as input, validate the user's credentials, and return an AuthenticationResponse if the authentication is successful. If the authentication fails, it should throw an appropriate exception.
    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwtToken = jwtService.generateToken(new HashMap<>(), user);
        return new AuthenticationResponse(jwtToken);
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
