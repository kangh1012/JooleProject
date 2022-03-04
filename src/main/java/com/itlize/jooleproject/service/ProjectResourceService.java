package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProjectResource;

import java.util.List;

public interface ProjectResourceService {
    ProjectResource save(ProjectResource resource);

    ProjectResource findById(Long id);

    List<ProjectResource> findAll();

    List<ProjectResource> findByProject(Project project);

    List<ProjectResource> findByProduct(Product product);

    void delete(ProjectResource resource);
}
