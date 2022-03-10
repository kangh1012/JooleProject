package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    Project save(Project project);

    Project createProjectByUserAndProjectName (User user, String projectName) throws Exception;

    Project findById(Long id);

    List<Project> findAll();

    List<Project> findByOwner(User owner);

    List<Project> findByProjectAddress(String projectAddress);

    List<Project> findByProjectType(String projectType);

    List<Project> findByProjectSize(String projectSize);

    void delete(Project project);


}
