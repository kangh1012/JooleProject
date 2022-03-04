package com.itlize.jooleproject.repository;

import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<List<Project>> findByOwner(User owner);

    Optional<List<Project>> findByProjectAddress(String projectAddress);

    Optional<List<Project>> findByProjectType(String projectType);

    Optional<List<Project>> findByProjectSize(String projectSize);
}
