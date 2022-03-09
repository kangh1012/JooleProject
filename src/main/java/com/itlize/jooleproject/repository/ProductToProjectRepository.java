package com.itlize.jooleproject.repository;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProductToProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductToProjectRepository extends JpaRepository<ProductToProject, Long> {
    Optional<List<ProductToProject>> findByProject(Project project);

    Optional<ProductToProject> findByProductAndProject(Product product, Project project);

    Optional<List<ProductToProject>> findByProduct(Product product);

}
