package com.dummy.jira.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dummy.jira.Interface.ProjectService;
import com.dummy.jira.Request.ProjectRequest;
import com.dummy.jira.Response.ProjectResponse;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @RequestBody ProjectRequest request){

        return new ResponseEntity<>(
                projectService.createProject(request),
                HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(){

        return ResponseEntity.ok(
                projectService.getAllProjects());

    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProject(
            @PathVariable String projectId){

        return ResponseEntity.ok(
                projectService.getProject(projectId));

    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable String projectId,
            @RequestBody ProjectRequest request){

        return ResponseEntity.ok(
                projectService.updateProject(projectId,request));

    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable String projectId){

        projectService.deleteProject(projectId);

        return ResponseEntity.noContent().build();

    }

}
