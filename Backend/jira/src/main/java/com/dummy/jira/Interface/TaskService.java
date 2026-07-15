package com.dummy.jira.Interface;

import java.util.List;

import com.dummy.jira.Enum.Priority;
import com.dummy.jira.Enum.TaskStatus;
import com.dummy.jira.Request.TaskRequest;
import com.dummy.jira.Response.TaskResponse;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse getTask(String taskId);

    TaskResponse updateTask(String taskId, TaskRequest request);

    void deleteTask(String taskId);

    TaskResponse changeStatus(String taskId, TaskStatus status);

    TaskResponse changePriority(String taskId, Priority priority);

    TaskResponse assignTask(String taskId, String assigneeId);

    List<TaskResponse> getTasksByProject(String projectId);

    List<TaskResponse> getTasksByUser(String userId);

    List<TaskResponse> getTasksByStatus(TaskStatus status);

}
