package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProductToProject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductToProjectService {
    ProductToProject save(ProductToProject resource);

    ProductToProject findById(Long id);

    ProductToProject findByProductAndProject(Product product, Project project);

    List<ProductToProject> findAll();

    List<ProductToProject> findByProject(Project project);

    List<ProductToProject> findByProduct(Product product);

    void delete(ProductToProject resource);
    void setPriceQuote(ProductToProject ptp, Double price);

    ProductToProject addProductToProject(Product product, Project project);

}
