package com.itlize.jooleproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProjectResource {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    private Double priceQuote;
    private LocalDateTime timeCreated;

    public ProjectResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPriceQuote() {
        return priceQuote;
    }

    public void setPriceQuote(Double priceQuote) {
        this.priceQuote = priceQuote;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }
}
