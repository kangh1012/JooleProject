package com.itlize.jooleproject.controller;


import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.service.ProjectService;
import com.itlize.jooleproject.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/add")
    public JsonResult<Void> add(Project project){
        LocalDateTime date = LocalDateTime.now();
        project.setTimeCreated(date);
        project.setLastModified(date);

        JsonResult<Void> result = new JsonResult<>();
        try{
            projectService.save(project);
            result.setState(200);
            result.setMessage("Successfully added product.");
        }catch(Exception e){
            result.setState(4000);
            result.setMessage("Error happens when trying to add product.");
        }

        return result;
    }

}
