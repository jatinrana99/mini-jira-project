package com.dummy.jira.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dummy.jira.Enum.Priority;
import com.dummy.jira.Enum.TaskStatus;
import com.dummy.jira.Interface.TaskService;
import com.dummy.jira.Request.TaskRequest;
import com.dummy.jira.Response.TaskResponse;

import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @RequestBody TaskRequest request) {

        return new ResponseEntity<>(
                taskService.createTask(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {

        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable String taskId) {

        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable String taskId,
            @RequestBody TaskRequest request) {

        return ResponseEntity.ok(
                taskService.updateTask(taskId, request));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable String taskId) {

        taskService.deleteTask(taskId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<TaskResponse> assignTask(
            @PathVariable String taskId,
            @PathVariable String userId) {

        return ResponseEntity.ok(
                taskService.assignTask(taskId, userId));
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<TaskResponse> changeStatus(
            @PathVariable String taskId,
            @RequestParam TaskStatus status) {

        return ResponseEntity.ok(
                taskService.changeStatus(taskId, status));
    }

    @PutMapping("/{taskId}/priority")
    public ResponseEntity<TaskResponse> changePriority(
            @PathVariable String taskId,
            @RequestParam Priority priority) {

        return ResponseEntity.ok(
                taskService.changePriority(taskId, priority));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponse>> getByProject(
            @PathVariable String projectId) {

        return ResponseEntity.ok(
                taskService.getTasksByProject(projectId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResponse>> getByUser(
            @PathVariable String userId) {

        return ResponseEntity.ok(
                taskService.getTasksByUser(userId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getByStatus(
            @PathVariable TaskStatus status) {

        return ResponseEntity.ok(
                taskService.getTasksByStatus(status));
    }
}
