package com.itlize.jooleproject.controller;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProjectResource;
import com.itlize.jooleproject.service.ProductService;
import com.itlize.jooleproject.service.ProjectResourceService;
import com.itlize.jooleproject.service.ProjectService;
import com.itlize.jooleproject.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JsonResult<Void> add(Product product){

        JsonResult<Void> result = new JsonResult<>();
        try{
            productService.save(product);
            result.setState(200);
            result.setMessage("Successfully added product.");
        }catch(Exception e){
            result.setState(4000);
            result.setMessage("Error happens when trying to add product.");
        }
        return result;
    }

    @RequestMapping("/addProductToProject")
    public ResponseEntity<?> addProductToProject(Product product, Project project){
         projectResourceService.findById()
    }


}
