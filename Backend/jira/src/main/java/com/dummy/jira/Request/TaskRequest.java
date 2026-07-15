package com.dummy.jira.Request;

import java.time.LocalDateTime;

import com.dummy.jira.Enum.Priority;
import com.dummy.jira.Enum.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Priority priority;

    @NotNull
    private TaskStatus status;

    private LocalDateTime dueDate;

    @NotBlank
    private String projectId;

    @NotBlank
    private String assigneeId;

    @NotBlank
    private String reporterId;

}
