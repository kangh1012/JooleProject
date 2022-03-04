package com.itlize.jooleproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long productId;


    @Column(name = "product_type")
    private String type;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "model_year")
    private Integer modelYear;

    @Column(name = "air_flow")
    private Integer airFlow;

    @CreatedDate
    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @LastModifiedDate
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @OneToMany(targetEntity = ProjectResource.class, mappedBy = "product", cascade =
            CascadeType.DETACH, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ProjectResource> resources;

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
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

    public Integer getAirFlow() {
        return airFlow;
    }

    public void setAirFlow(Integer airFlow) {
        this.airFlow = airFlow;
    }
}
