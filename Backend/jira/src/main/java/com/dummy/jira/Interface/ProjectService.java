package com.dummy.jira.Interface;

import java.util.List;

import com.dummy.jira.Request.ProjectRequest;
import com.dummy.jira.Response.ProjectResponse;

public interface ProjectService {

    ProjectResponse createProject(ProjectRequest request);

    List<ProjectResponse> getAllProjects();

    ProjectResponse getProject(String projectId);

    ProjectResponse updateProject(String projectId,
                                  ProjectRequest request);

    void deleteProject(String prsojectId);
    
}
