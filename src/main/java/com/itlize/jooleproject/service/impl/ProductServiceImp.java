package com.itlize.jooleproject.service.impl;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.repository.ProductRepository;
import com.itlize.jooleproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product createNewProduct(){
        Product product = new Product();
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
        return repository.findByType(type).orElse(null);
    }

    public List<Product> findByCategory(String category) {
        return repository.findByCategory(category).orElse(null);
    }

    @Override
    public List<Product> findByManufacturer(String manufacturer) {
        return repository.findByManufacturer(manufacturer).orElse(null);
    }

    @Override
    public List<Product> findByModel(String model) {
        return repository.findByModel(model).orElse(null);
    }

    @Override
    public List<Product> findByModelYearBetween(Integer modelYear, Integer modelYear2) {
        return repository.findByModelYearBetween(modelYear, modelYear2).orElse(null);
    }

    @Override
    public List<Product> findByAirFlowBetween(Integer airFlow, Integer airFlow2) {
        return repository.findByAirFlowBetween(airFlow, airFlow2).orElse(null);
    }

    @Override
    public Map<String, List<String>> findCategoryAndType() {
        List<List<String>> result = repository.findCategoryAndType().orElse(null);
        if(result != null) {
            Map<String, List<String>> map = new HashMap<>();
            for(List<String> list : result) {
                String key = list.get(0);
                String value = list.get(1);
                if(!map.containsKey(key)) {
                    List<String> l = new ArrayList<>();
                    l.add(value);
                    map.put(key, l);
                } else {
                    map.get(key).add(value);
                }
            }

            return map;
        }

        return null;
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }
}
