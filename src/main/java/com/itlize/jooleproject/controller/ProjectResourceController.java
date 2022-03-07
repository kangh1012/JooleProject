package com.itlize.jooleproject.controller;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.ProjectResource;
import com.itlize.jooleproject.service.ProductService;
import com.itlize.jooleproject.service.ProjectResourceService;
import com.itlize.jooleproject.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/projectResource")
public class ProjectResourceController {
    @Autowired
    private ProjectResourceService projectResourceService;

    @RequestMapping("/add_relationship")
    public JsonResult<Void> addRelationship(ProjectResource pr){
        LocalDateTime date = LocalDateTime.now();
        pr.setTimeCreated(date);
        pr.setLastModified(date);

        JsonResult<Void> result = new JsonResult<>();
        try{
            projectResourceService.save(pr);
            result.setState(200);
            result.setMessage("Successfully added product.");
        }catch(Exception e){
            result.setState(4000);
            result.setMessage("Error happens when trying to add product.");
        }

        return result;
    }

}