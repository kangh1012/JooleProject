package com.itlize.jooleproject.controller;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProjectResource;
import com.itlize.jooleproject.service.ProductService;
import com.itlize.jooleproject.service.ProjectResourceService;
import com.itlize.jooleproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectResourceService projectResourceService;

    @RequestMapping("/createProduct")
    public ResponseEntity<?> createProduct(Product product){

        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @RequestMapping("/addProductToProject")
    public ResponseEntity<?> addProductToProject(Product product, Project project){
        ProjectResource pr = projectResourceService.findByProductAndProject(product, project);

        return new ResponseEntity<>(pr, HttpStatus.CREATED);
    }


}
