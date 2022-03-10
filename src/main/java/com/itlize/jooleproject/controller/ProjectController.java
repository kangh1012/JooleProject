package com.itlize.jooleproject.controller;


import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.service.ProjectService;
import com.itlize.jooleproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @RequestMapping("/createProject")
    public ResponseEntity<?> createProject(@RequestParam(name = "username") String username,
                                           @RequestParam(name = "projectName") String projectName) throws Exception {
        User findUser = userService.findByUsername(username);
        if (findUser == null){
            return new ResponseEntity<>("{\"message: \" System do not have this user yet. }", HttpStatus.BAD_REQUEST);
        }

        Project project = projectService.createProjectByUserAndProjectName(findUser,projectName);
        return new ResponseEntity<>(project, HttpStatus.CREATED);

    }

    @RequestMapping("/fetchProjectById")
    public ResponseEntity<?> fetchProjectById(@RequestParam(name = "projectId") Long id){
        Project project = projectService.findById(id);
        return new ResponseEntity<>(project, HttpStatus.FOUND);
    }

    @RequestMapping("/deleteProjectById")
    public ResponseEntity<?> deleteProjectById(@RequestParam(name = "projectId") Long id){
        Project project = projectService.findById(id);
        projectService.delete(project);
        return new ResponseEntity<>("{\"message: \" project deleted.}", HttpStatus.OK);
    }

    @RequestMapping("/updateProject")
    public ResponseEntity<?> updateProject(@RequestParam(name = "projectId") Long id,
                                           @RequestParam(name = "projectName", required = false) String name,
                                           @RequestParam(name = "address", required = false) String address,
                                           @RequestParam(name = "size", required = false)String size,
                                           @RequestParam(name = "type", required = false) String type){
        Project project = projectService.findById(id);
        project.setProjectName(name);
        project.setProjectAddress(address);
        project.setProjectSize(size);
        project.setProjectType(type);
        Project result = projectService.save(project);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
