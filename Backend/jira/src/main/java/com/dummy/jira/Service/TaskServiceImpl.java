package com.dummy.jira.Service;

import org.springframework.stereotype.Service;

import com.dummy.jira.Dto.Project;
import com.dummy.jira.Dto.Task;
import com.dummy.jira.Dto.User;
import com.dummy.jira.Enum.Priority;
import com.dummy.jira.Enum.TaskStatus;
import com.dummy.jira.Exception.ResourceNotFoundException;
import com.dummy.jira.Interface.TaskService;
import com.dummy.jira.Repository.ProjectRepository;
import com.dummy.jira.Repository.TaskRepository;
import com.dummy.jira.Repository.UserRepository;
import com.dummy.jira.Request.TaskRequest;
import com.dummy.jira.Response.TaskResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponse createTask(TaskRequest request) {

        Project project = projectRepository.findByProjectId(request.getProjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        User assignee = userRepository.findByUserId(request.getAssigneeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignee not found"));

        User reporter = userRepository.findByUserId(request.getReporterId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reporter not found"));

        Task task = new Task();

        task.setTaskId(UUID.randomUUID().toString());

        task.setTitle(request.getTitle());

        task.setDescription(request.getDescription());

        task.setPriority(request.getPriority());

        task.setStatus(request.getStatus());

        task.setDueDate(request.getDueDate());

        task.setProject(project);

        task.setAssignee(assignee);

        task.setReporter(reporter);

        task.setCreatedDateTime(LocalDateTime.now());

        task.setUpdatedDateTime(LocalDateTime.now());

        taskRepository.save(task);

        return mapToResponse(task);

    }

    @Override
    public List<TaskResponse> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    public TaskResponse getTask(String taskId) {

        Task task = taskRepository.findByTaskId(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        return mapToResponse(task);

    }

    @Override
    public TaskResponse updateTask(String taskId,
                                   TaskRequest request) {

        Task task = taskRepository.findByTaskId(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        Project project = projectRepository.findByProjectId(request.getProjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        User assignee = userRepository.findByUserId(request.getAssigneeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignee not found"));

        User reporter = userRepository.findByUserId(request.getReporterId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reporter not found"));

        task.setTitle(request.getTitle());

        task.setDescription(request.getDescription());

        task.setPriority(request.getPriority());

        task.setStatus(request.getStatus());

        task.setDueDate(request.getDueDate());

        task.setProject(project);

        task.setAssignee(assignee);

        task.setReporter(reporter);

        task.setUpdatedDateTime(LocalDateTime.now());

        taskRepository.save(task);

        return mapToResponse(task);

    }

    @Override
    public void deleteTask(String taskId) {

        Task task = taskRepository.findByTaskId(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);

    }

    private TaskResponse mapToResponse(Task task) {

        return TaskResponse.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .dueDate(task.getDueDate())

                .projectId(task.getProject().getProjectId())
                .projectName(task.getProject().getProjectName())

                .assigneeId(task.getAssignee().getUserId())
                .assigneeName(
                        task.getAssignee().getFirstName() + " "
                                + task.getAssignee().getLastName())

                .reporterId(task.getReporter().getUserId())
                .reporterName(
                        task.getReporter().getFirstName() + " "
                                + task.getReporter().getLastName())

                .build();

    }

   @Override
public TaskResponse assignTask(String taskId, String assigneeId) {

    Task task = taskRepository.findByTaskId(taskId)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Task not found"));

    User assignee = userRepository.findByUserId(assigneeId)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));

    task.setAssignee(assignee);
    task.setUpdatedDateTime(LocalDateTime.now());

    taskRepository.save(task);

    return mapToResponse(task);
}

@Override
public TaskResponse changeStatus(String taskId, TaskStatus status) {

    Task task = taskRepository.findByTaskId(taskId)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Task not found"));

    task.setStatus(status);
    task.setUpdatedDateTime(LocalDateTime.now());

    taskRepository.save(task);

    return mapToResponse(task);
}

@Override
public TaskResponse changePriority(String taskId, Priority priority) {

    Task task = taskRepository.findByTaskId(taskId)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Task not found"));

    task.setPriority(priority);
    task.setUpdatedDateTime(LocalDateTime.now());

    taskRepository.save(task);

    return mapToResponse(task);
}

@Override
public List<TaskResponse> getTasksByProject(String projectId) {

    return taskRepository.findByProject_ProjectId(projectId)
            .stream()
            .map(this::mapToResponse)
            .toList();
}

@Override
public List<TaskResponse> getTasksByUser(String userId) {

    return taskRepository.findByAssignee_UserId(userId)
            .stream()
            .map(this::mapToResponse)
            .toList();
}

@Override
public List<TaskResponse> getTasksByStatus(TaskStatus status) {

    return taskRepository.findByStatus(status)
            .stream()
            .map(this::mapToResponse)
            .toList();
}
}
