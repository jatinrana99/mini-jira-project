package com.dummy.jira.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private long totalUsers;

    private long totalProjects;

    private long totalTasks;

    private long todoTasks;

    private long inProgressTasks;

    private long doneTasks;

}
