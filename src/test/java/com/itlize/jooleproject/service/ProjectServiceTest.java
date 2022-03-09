package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.service.ProjectService;
import com.itlize.jooleproject.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class ProjectServiceTest {
    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Test
    public void saveTest() {
        Project original = new Project();
        original.setProjectName("Sample1");
        original.setProjectType("construction");
        original.setProjectSize("Medium");
        original.setProjectAddress("Las Vegas, NV");
        User owner = userService.findByUsername("test1");
        original.setOwner(owner);
        Set<Project> projects = new HashSet<>();
        projects.add(original);
        owner.setProjects(projects);

        Project saved = projectService.save(original);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getProjectName()).isEqualTo("Sample1");
    }

    @Test
    public void findByIdTest() {
        Project original = new Project();
        original.setProjectName("Sample1");
        original.setProjectType("construction");
        original.setProjectSize("Medium");
        original.setProjectAddress("Las Vegas, NV");

        Project actual = projectService.findById(1L);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getProjectName()).isEqualTo(original.getProjectName());
        Assertions.assertThat(actual.getProjectType()).isEqualTo(original.getProjectType());
        Assertions.assertThat(actual.getProjectSize()).isEqualTo(original.getProjectSize());
        Assertions.assertThat(actual.getProjectAddress()).isEqualTo(original.getProjectAddress());
    }

    @Test
    public void findAllTest() {
        List<Project> projects = projectService.findAll();

        Assertions.assertThat(projects.size()).isEqualTo(1);
        Assertions.assertThat(projects.get(0).getProjectName()).isEqualTo("Sample1");
    }

    @Test
    @Transactional
    public void findByOwnerTest() {
        Project project1 = new Project();
        project1.setProjectName("Sample2");
        project1.setProjectType("repair");
        project1.setProjectSize("Small");
        project1.setProjectAddress("New York, NY");
        User owner1 = userService.findByUsername("test1");
        project1.setOwner(owner1);
        Set<Project> projects = owner1.getProjects();
        projects.add(project1);

        Project project2 = new Project();
        project2.setProjectName("Sample3");
        project2.setProjectType("construction");
        project2.setProjectSize("Medium");
        project2.setProjectAddress("New York, NY");
        User owner2 = userService.findByUsername("test2");
        project2.setOwner(owner2);
        projects = new HashSet<>();
        projects.add(project2);
        owner2.setProjects(projects);

        projectService.save(project1);
        projectService.save(project2);

        List<Project> projectList = projectService.findByOwner(owner1);

        for(Project project: projectList) {
            Assertions.assertThat(project.getOwner().getUsername()).isEqualTo("test1");
        }
    }

    @Test
    public void findByProjectAddressTest() {
        Project project1 = new Project();
        project1.setProjectName("Sample2");
        project1.setProjectType("repair");
        project1.setProjectSize("Small");
        project1.setProjectAddress("New York, NY");

        Project project2 = new Project();
        project2.setProjectName("Sample3");
        project2.setProjectType("construction");
        project2.setProjectSize("Medium");
        project2.setProjectAddress("New York, NY");

        projectService.save(project1);
        projectService.save(project2);

        List<Project> projects = projectService.findByProjectAddress("New York, NY");

        for(Project project: projects) {
            Assertions.assertThat(project.getProjectAddress()).isEqualTo("New York, NY");
        }
    }

    @Test
    public void findByProjectTypeTest() {
        List<Project> projects = projectService.findByProjectType("construction");

        for(Project project: projects) {
            Assertions.assertThat(project.getProjectType()).isEqualTo("construction");
        }
    }

    @Test
    public void findByProjectSizeTest() {
        List<Project> projects = projectService.findByProjectSize("Medium");

        for(Project project: projects) {
            Assertions.assertThat(project.getProjectSize()).isEqualTo("Medium");
        }
    }

    @Test
    public void deleteTest() {
        Project deletedProject = projectService.findById(1L);

        projectService.delete(deletedProject);

        deletedProject = projectService.findById(1L);

        Assertions.assertThat(deletedProject).isNull();
    }
}
