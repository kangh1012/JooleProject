package com.itlize.jooleproject.repository;

import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner(User owner);

    List<Project> findByProjectAddress(String projectAddress);

    List<Project> findByProjectType(String projectType);

    List<Project> findByProjectSize(String projectSize);
}
