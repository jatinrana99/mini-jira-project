package com.dummy.jira.Response;

import java.time.LocalDateTime;

import com.dummy.jira.Enum.Priority;
import com.dummy.jira.Enum.TaskStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {

    private String taskId;

    private String title;

    private String description;

    private Priority priority;

    private TaskStatus status;

    private LocalDateTime dueDate;

    private String projectId;

    private String projectName;

    private String assigneeId;

    private String assigneeName;

    private String reporterId;

    private String reporterName;

}
