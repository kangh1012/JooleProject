package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findByType(String type);

    List<Product> findByManufacturer(String manufacturer);

    List<Product> findByModel(String model);

    List<Product> findByModelYearBetween(Integer modelYear, Integer modelYear2);

    List<Product> findByAirFlowBetween(Integer airFlow, Integer airFlow2);

    void delete(Product product);
}
