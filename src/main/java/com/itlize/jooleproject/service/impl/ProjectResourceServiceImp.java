package com.itlize.jooleproject.service.impl;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProjectResource;
import com.itlize.jooleproject.repository.ProjectResourceRepository;
import com.itlize.jooleproject.service.ProjectResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectResourceServiceImp implements ProjectResourceService {
    @Autowired
    private ProjectResourceRepository repository;

    @Override
    public ProjectResource save(ProjectResource resource) {
        return repository.save(resource);
    }

    @Override
    public ProjectResource findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ProjectResource> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProjectResource> findByProject(Project project) {
        return repository.findByProject(project).orElse(null);
    }

    @Override
    public List<ProjectResource> findByProduct(Product product) {
        return repository.findByProduct(product).orElse(null);
    }

    @Override
    public void delete(ProjectResource resource) {
        repository.delete(resource);
    }
}
