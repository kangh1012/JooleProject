package com.itlize.jooleproject.repository;

import com.itlize.jooleproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByType(String type);

    Optional<List<Product>> findByManufacturer(String manufacturer);

    Optional<List<Product>> findByModel(String model);

    Optional<List<Product>> findByModelYearBetween(Integer modelYear, Integer modelYear2);

    Optional<List<Product>> findByAirFlowBetween(Integer airFlow, Integer airFlow2);

    @Query(value = "SELECT p.category, p.type FROM Product p GROUP BY p.type")
    Optional<List<List<String>>> findCategoryAndType();
}
