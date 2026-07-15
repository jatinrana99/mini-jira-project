package com.dummy.jira.Interface;

import com.dummy.jira.Request.LoginRequest;
import com.dummy.jira.Request.RegisterRequest;
import com.dummy.jira.Response.LoginResponse;
import com.dummy.jira.Response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
