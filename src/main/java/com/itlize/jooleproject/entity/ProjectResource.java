package com.itlize.jooleproject.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_resource")
@EntityListeners(AuditingEntityListener.class)
public class ProjectResource {
    @Id
    @GeneratedValue
    @Column(name = "pr_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "project_id")
    Project project;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    Product product;

    @Column(name = "price_quote")
    private Double priceQuote;

    @CreatedDate
    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @LastModifiedDate
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

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

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
