package com.itlize.jooleproject.service.impl;

import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.repository.ProjectRepository;
import com.itlize.jooleproject.repository.UserRepository;
import com.itlize.jooleproject.service.ProjectService;
import com.itlize.jooleproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Project save(Project project) {
        return repository.save(project);
    }

    @Override
    public Project createProjectByUsernameAndProjectName(String username,String projectName) throws Exception{


        User testuser = userRepository.findById(username).orElse(null);
        if(testuser  == null){
            throw new Exception("user not found.");
        }
        List<Project> projects = repository.findByOwner(testuser).orElse(null);
        for (Project p : projects){
            if (p.getProjectName() == projectName){
                throw new Exception("This project is existing in current user.");
            }
        }
        Project project = new Project();
        project.setProjectName(projectName);
        project.setOwner(testuser);

        return repository.save(project);
    }

    @Override
    public Project findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Project> findByOwner(User owner) {
        return repository.findByOwner(owner).orElse(null);
    }

    @Override
    public List<Project> findByProjectAddress(String projectAddress) {
        return repository.findByProjectAddress(projectAddress).orElse(null);
    }

    @Override
    public List<Project> findByProjectType(String projectType) {
        return repository.findByProjectType(projectType).orElse(null);
    }

    @Override
    public List<Project> findByProjectSize(String projectSize) {
        return repository.findByProjectSize(projectSize).orElse(null);
    }

    @Override
    public void delete(Project project) {
        repository.delete(project);
    }
}
