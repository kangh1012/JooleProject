package com.itlize.jooleproject.repository;

import com.itlize.jooleproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByType(String type);

    List<Product> findByManufacturer(String manufacturer);

    List<Product> findByModel(String model);

    List<Product> findByModelYearBetween(Integer modelYear, Integer modelYear2);

    List<Product> findByAirFlowBetween(Integer airFlow, Integer airFlow2);
}
