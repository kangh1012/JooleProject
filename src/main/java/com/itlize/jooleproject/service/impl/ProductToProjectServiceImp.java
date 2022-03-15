package com.itlize.jooleproject.service.impl;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProductToProject;
import com.itlize.jooleproject.repository.ProductToProjectRepository;
import com.itlize.jooleproject.service.ProductToProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductToProjectServiceImp implements ProductToProjectService {
    @Autowired
    private ProductToProjectRepository repository;

    @Override
    public ProductToProject save(ProductToProject resource) {
        return repository.save(resource);
    }

    @Override
    public ProductToProject findByProductAndProject(Product product, Project project) {
        return repository.findByProductAndProject(product, project).orElse(null);
    }

    @Override
    public ProductToProject findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ProductToProject> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductToProject> findByProject(Project project) {
        return repository.findByProject(project).orElse(null);
    }

    @Override
    public List<ProductToProject> findByProduct(Product product) {
        return repository.findByProduct(product).orElse(null);
    }

    @Override
    public void delete(ProductToProject resource) {
        repository.delete(resource);
    }

    @Override
    public void setPriceQuote(ProductToProject ptp, Double price) {
        ptp.setPriceQuote(price);
        repository.save(ptp);
    }

    @Override
    public ProductToProject addProductToProject(Product product, Project project) {
        ProductToProject ptp = new ProductToProject();
        ptp.setProduct(product);
        ptp.setProject(project);
        return repository.save(ptp);
    }




}
