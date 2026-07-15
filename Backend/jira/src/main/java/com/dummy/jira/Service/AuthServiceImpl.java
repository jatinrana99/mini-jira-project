package com.dummy.jira.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dummy.jira.Dto.User;
import com.dummy.jira.Enum.Role;
import com.dummy.jira.Exception.DuplicateResourceException;
import com.dummy.jira.Exception.ResourceNotFoundException;
import com.dummy.jira.Interface.AuthService;
import com.dummy.jira.Repository.UserRepository;
import com.dummy.jira.Request.LoginRequest;
import com.dummy.jira.Request.RegisterRequest;
import com.dummy.jira.Response.LoginResponse;
import com.dummy.jira.Response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }

        User user = new User();

        user.setUserId(UUID.randomUUID().toString());

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER);

        user.setCreatedDateTime(LocalDateTime.now());

        user.setUpdatedDateTime(LocalDateTime.now());

        userRepository.save(user);

        return UserResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                // .role(user.getRole())
                .createdDateTime(user.getCreatedDateTime())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid Email or Password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResourceNotFoundException("Invalid Email or Password");
        }

        return LoginResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .role(user.getRole().name())
                .token("LOGIN_SUCCESS")
                .build();
    }
    
}
