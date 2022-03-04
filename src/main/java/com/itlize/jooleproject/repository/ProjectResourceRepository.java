package com.itlize.jooleproject.repository;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProjectResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Long> {
    List<ProjectResource> findByProject(Project project);

    List<ProjectResource> findByProduct(Product product);
}
