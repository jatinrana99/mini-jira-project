package com.dummy.jira.Request;

import lombok.Data;

@Data
public class ProjectRequest {


    private String projectName;

    private String projectDescription;

    // UserId of owner
    private String ownerId;
    
}
