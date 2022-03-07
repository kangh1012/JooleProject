package com.itlize.jooleproject;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProjectResource;
import com.itlize.jooleproject.service.ProductService;
import com.itlize.jooleproject.service.ProjectResourceService;
import com.itlize.jooleproject.service.ProjectService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class ProjectResourceServiceTest {
    @Autowired
    ProjectResourceService projectResourceService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProductService productService;

    @Test
    public void SaveTest() {
        ProjectResource resource = new ProjectResource();
        Project project = projectService.findById(4L);
        Product product = productService.findById(6L);
        resource.setProject(project);
        resource.setProduct(product);
        resource.setPriceQuote(250.00);
        Set<ProjectResource> resources = new HashSet<>();
        resources.add(resource);
        project.setResources(resources);
        product.setResources(resources);

        ProjectResource saved = projectResourceService.save(resource);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getPriceQuote()).isEqualTo(250.00);
    }

    @Test
    public void findByIdTest() {
        ProjectResource resource = projectResourceService.findById(13L);

        Assertions.assertThat(resource).isNotNull();
    }

    @Test
    public void findAllTest() {
        ProjectResource resource = new ProjectResource();
        Project project = projectService.findById(5L);
        Product product = productService.findById(11L);
        resource.setProject(project);
        resource.setProduct(product);
        resource.setPriceQuote(347.00);
        Set<ProjectResource> resources = new HashSet<>();
        resources.add(resource);
        project.setResources(resources);
        product.setResources(resources);

        projectResourceService.save(resource);

        List<ProjectResource> resourceList = projectResourceService.findAll();

        Assertions.assertThat(resourceList.size()).isEqualTo(2);
    }

    @Test
    public void findByProjectTest() {
        Project project = projectService.findById(5L);
        List<ProjectResource> resources = projectResourceService.findByProject(project);

        for(ProjectResource resource: resources) {
            Assertions.assertThat(resource.getProject().getProjectName()).isEqualTo(project.getProjectName());
        }
    }

    @Test
    public void findByProductTest() {
        Product product = productService.findById(11L);
        List<ProjectResource> resources = projectResourceService.findByProduct(product);

        for(ProjectResource resource: resources) {
            Assertions.assertThat(resource.getProduct().getProductId()).isEqualTo(product.getProductId());
        }
    }

    @Test
    public void deleteTest() {
        ProjectResource deletedResource = projectResourceService.findById(13L);

        projectResourceService.delete(deletedResource);

        deletedResource = projectResourceService.findById(13L);

        Assertions.assertThat(deletedResource).isNull();
    }
}
