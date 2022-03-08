package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProjectResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectResourceService {
    ProjectResource save(ProjectResource resource);

    ProjectResource findById(Long id);

    ProjectResource findByProductAndProject(Product product,Project project);

    List<ProjectResource> findAll();

    List<ProjectResource> findByProject(Project project);

    List<ProjectResource> findByProduct(Product product);

    void delete(ProjectResource resource);
}
