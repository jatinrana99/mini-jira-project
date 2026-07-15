package com.dummy.jira.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {

     private String projectId;

    private String projectName;

    private String projectDescription;

    private String ownerId;

    private String ownerName;
    
}
