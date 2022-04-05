package com.itlize.jooleproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "category")
    private String category;

    @Column(name = "product_type")
    private String type;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "series")
    private String series;

    @Column(name = "model")
    private String model;

    @Column(name = "model_year")
    private Integer modelYear;

    @Column(name = "air_flow")
    private Integer airFlow;

    @Column(name = "max_power")
    private Double maxPower;

    @Column(name = "sound")
    private Integer sound;

    @Column(name = "fan_sweep_diameter")
    private Integer fanSweepDiameter;

    @CreatedDate
    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @LastModifiedDate
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @Column(name = "image_path")
    private String imagePath;

    @OneToMany(targetEntity = ProductToProject.class, mappedBy = "product", cascade =
            CascadeType.DETACH, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ProductToProject> resources;

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

    public Set<ProductToProject> getResources() {
        return resources;
    }

    public void setResources(Set<ProductToProject> resources) {
        this.resources = resources;
    }

    public Integer getAirFlow() {
        return airFlow;
    }

    public void setAirFlow(Integer airFlow) {
        this.airFlow = airFlow;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Double getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Double maxPower) {
        this.maxPower = maxPower;
    }

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public Integer getFanSweepDiameter() {
        return fanSweepDiameter;
    }

    public void setFanSweepDiameter(Integer fanSweepDiameter) {
        this.fanSweepDiameter = fanSweepDiameter;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                ", modelYear=" + modelYear +
                ", airFlow=" + airFlow +
                ", maxPower=" + maxPower +
                ", sound=" + sound +
                ", fanSweepDiameter=" + fanSweepDiameter +
                ", timeCreated=" + timeCreated +
                ", lastModified=" + lastModified +
                ", imagePath='" + imagePath + '\'' +
                ", resources=" + resources +
                '}';
    }
}
