package com.dummy.jira.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dummy.jira.Dto.Project;
import com.dummy.jira.Dto.User;
import com.dummy.jira.Exception.ResourceNotFoundException;
import com.dummy.jira.Interface.ProjectService;
import com.dummy.jira.Repository.ProjectRepository;
import com.dummy.jira.Repository.UserRepository;
import com.dummy.jira.Request.ProjectRequest;
import com.dummy.jira.Response.ProjectResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {

        User owner = userRepository.findByUserId(request.getOwnerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Owner not found"));

        Project project = new Project();

        project.setProjectId(UUID.randomUUID().toString());

        project.setProjectName(request.getProjectName());

        project.setProjectDescription(request.getProjectDescription());

        project.setOwner(owner);

        project.setCreatedDateTime(LocalDateTime.now());

        project.setUpdatedDateTime(LocalDateTime.now());

        projectRepository.save(project);

        return map(project);

    }

    @Override
    public List<ProjectResponse> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::map)
                .toList();

    }

    @Override
    public ProjectResponse getProject(String projectId) {

        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        return map(project);

    }

    @Override
    public ProjectResponse updateProject(String projectId,
                                         ProjectRequest request) {

        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        User owner = userRepository.findByUserId(request.getOwnerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Owner not found"));

        project.setProjectName(request.getProjectName());

        project.setProjectDescription(request.getProjectDescription());

        project.setOwner(owner);

        project.setUpdatedDateTime(LocalDateTime.now());

        projectRepository.save(project);

        return map(project);

    }

    @Override
    public void deleteProject(String projectId) {

        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        projectRepository.delete(project);

    }

    private ProjectResponse map(Project project){

        return ProjectResponse.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .projectDescription(project.getProjectDescription())
                .ownerId(project.getOwner().getUserId())
                .ownerName(project.getOwner().getFirstName()+" "+project.getOwner().getLastName())
                .build();

    }

}