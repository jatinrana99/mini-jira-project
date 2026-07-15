package com.dummy.jira.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dummy.jira.Dto.Task;
import com.dummy.jira.Enum.Priority;
import com.dummy.jira.Enum.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
    Optional<Task> findByTaskId(String taskId);

    List<Task> findByProject_ProjectId(String projectId);

    List<Task> findByAssignee_UserId(String userId);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(Priority priority);
}
