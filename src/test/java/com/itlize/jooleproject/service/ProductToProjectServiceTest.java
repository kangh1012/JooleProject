package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProductToProject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class ProductToProjectServiceTest {
    @Autowired
    ProductToProjectService productToProjectService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProductService productService;

    @Test
    public void SaveTest() {
        ProductToProject resource = new ProductToProject();
        Project project = projectService.findById(4L);
        Product product = productService.findById(6L);
        resource.setProject(project);
        resource.setProduct(product);
        resource.setPriceQuote(250.00);
        Set<ProductToProject> resources = new HashSet<>();
        resources.add(resource);
        project.setResources(resources);
        product.setResources(resources);

        ProductToProject saved = productToProjectService.save(resource);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getPriceQuote()).isEqualTo(250.00);
    }

    @Test
    public void findByIdTest() {
        ProductToProject resource = productToProjectService.findById(13L);

        Assertions.assertThat(resource).isNotNull();
    }

    @Test
    public void findAllTest() {
        ProductToProject resource = new ProductToProject();
        Project project = projectService.findById(5L);
        Product product = productService.findById(11L);
        resource.setProject(project);
        resource.setProduct(product);
        resource.setPriceQuote(347.00);
        Set<ProductToProject> resources = new HashSet<>();
        resources.add(resource);
        project.setResources(resources);
        product.setResources(resources);

        productToProjectService.save(resource);

        List<ProductToProject> resourceList = productToProjectService.findAll();

        Assertions.assertThat(resourceList.size()).isEqualTo(2);
    }

    @Test
    public void findByProjectTest() {
        Project project = projectService.findById(5L);
        List<ProductToProject> resources = productToProjectService.findByProject(project);

        for(ProductToProject resource: resources) {
            Assertions.assertThat(resource.getProject().getProjectName()).isEqualTo(project.getProjectName());
        }
    }

    @Test
    public void findByProductTest() {
        Product product = productService.findById(11L);
        List<ProductToProject> resources = productToProjectService.findByProduct(product);

        for(ProductToProject resource: resources) {
            Assertions.assertThat(resource.getProduct().getProductId()).isEqualTo(product.getProductId());
        }
    }

    @Test
    public void findByProductAndProjectTest(){
        Product product = productService.findById((long) 2);
        Project project = projectService.findById((long) 1);
        ProductToProject pr = productToProjectService.findByProductAndProject(product, project);
        Assertions.assertThat(pr.getId()).isEqualTo((long) 1);
    }

    @Test
    public void deleteTest() {
        ProductToProject deletedResource = productToProjectService.findById(13L);

        productToProjectService.delete(deletedResource);

        deletedResource = productToProjectService.findById(13L);

        Assertions.assertThat(deletedResource).isNull();
    }
}
