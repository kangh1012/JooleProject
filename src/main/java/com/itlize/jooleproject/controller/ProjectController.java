package com.itlize.jooleproject.controller;


import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.service.ProjectService;
import com.itlize.jooleproject.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/addProject")
    public ResponseEntity<?> add(Project project, User user){

            project.setOwner(user);
            projectService.save(project);
            return new ResponseEntity<>(project, HttpStatus.CREATED);




    }

}
