package com.dummy.jira.Response;

import java.time.LocalDateTime;

import javax.management.relation.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

      private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private LocalDateTime createdDateTime;
    
}
