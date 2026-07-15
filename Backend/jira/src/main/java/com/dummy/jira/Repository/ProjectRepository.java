package com.dummy.jira.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dummy.jira.Dto.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

    Optional<Project> findByProjectId(String projectId);

    boolean existsByProjectId(String projectId);
    
}
