package com.dummy.jira.Interface;

import java.util.List;

import com.dummy.jira.Request.UserRequest;
import com.dummy.jira.Response.UserResponse;

public interface UserService {
    
   UserResponse createUser(UserRequest request);

    UserResponse getUser(String userId);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(String userId, UserRequest request);

    void deleteUser(String userId);
}
