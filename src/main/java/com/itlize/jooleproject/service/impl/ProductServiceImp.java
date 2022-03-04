package com.itlize.jooleproject.service.impl;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.repository.ProductRepository;
import com.itlize.jooleproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findByType(String type) {
        return repository.findByType(type);
    }

    @Override
    public List<Product> findByManufacturer(String manufacturer) {
        return repository.findByManufacturer(manufacturer);
    }

    @Override
    public List<Product> findByModel(String model) {
        return repository.findByModel(model);
    }

    @Override
    public List<Product> findByModelYearBetween(Integer modelYear, Integer modelYear2) {
        return repository.findByModelYearBetween(modelYear, modelYear2);
    }

    @Override
    public List<Product> findByAirFlowBetween(Integer airFlow, Integer airFlow2) {
        return repository.findByAirFlowBetween(airFlow, airFlow2);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }
}
