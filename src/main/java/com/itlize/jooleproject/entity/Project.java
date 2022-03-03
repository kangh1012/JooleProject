package com.itlize.jooleproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private Long projectId;

    private String projectName;
    private String projectAddress;
    private String projectType;
    private String projectSize;
    private LocalDateTime timeCreated;
    private LocalDateTime lastModified;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User owner;

    @OneToMany(mappedBy = "project")
    private Set<ProjectResource> resources;

    public Project() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(String projectSize) {
        this.projectSize = projectSize;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Set<ProjectResource> getResources() {
        return resources;
    }

    public void setResources(Set<ProjectResource> resources) {
        this.resources = resources;
    }
}
