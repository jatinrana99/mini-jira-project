package com.dummy.jira.Service;

import org.springframework.stereotype.Service;

import com.dummy.jira.Enum.TaskStatus;
import com.dummy.jira.Interface.DashboardService;
import com.dummy.jira.Repository.ProjectRepository;
import com.dummy.jira.Repository.TaskRepository;
import com.dummy.jira.Repository.UserRepository;
import com.dummy.jira.Response.DashboardResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    @Override
    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()

                .totalUsers(userRepository.count())

                .totalProjects(projectRepository.count())

                .totalTasks(taskRepository.count())

                .todoTasks(taskRepository.findByStatus(TaskStatus.TODO).size())

                .inProgressTasks(taskRepository.findByStatus(TaskStatus.IN_PROGRESS).size())

                .doneTasks(taskRepository.findByStatus(TaskStatus.DONE).size())

                .build();

    }

}
