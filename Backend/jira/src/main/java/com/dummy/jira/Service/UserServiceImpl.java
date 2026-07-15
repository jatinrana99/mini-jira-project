package com.dummy.jira.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.dummy.jira.Dto.User;
import com.dummy.jira.Enum.Role;
import com.dummy.jira.Exception.DuplicateResourceException;
import com.dummy.jira.Exception.ResourceNotFoundException;
import com.dummy.jira.Interface.UserService;
import com.dummy.jira.Repository.UserRepository;
import com.dummy.jira.Request.UserRequest;
import com.dummy.jira.Response.UserResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = new User();

        user.setUserId(UUID.randomUUID().toString());

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setEmail(request.getEmail());

        // Temporary (we'll encode it after Spring Security)
        user.setPassword(request.getPassword());

        // user.setRoleType("USER");
        user.setRole(Role.USER);

        user.setCreatedDateTime(LocalDateTime.now());

        user.setUpdatedDateTime(LocalDateTime.now());

        user = userRepository.save(user);

        return mapToResponse(user);
    }

    @Override
    public UserResponse getUser(String userId) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(String userId, UserRequest request) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setEmail(request.getEmail());

        user.setUpdatedDateTime(LocalDateTime.now());

        userRepository.save(user);

        return mapToResponse(user);
    }

    @Override
    public void deleteUser(String userId) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }

    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                // .role(user.getRole())
                .createdDateTime(user.getCreatedDateTime())
                .build();
    }
}