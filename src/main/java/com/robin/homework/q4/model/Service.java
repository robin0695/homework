package com.robin.homework.q4.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Service name is required.")
    @Basic(optional = false)
    private String name;

    private Double price;

    private String serviceUrl;

    public Service(Long id, @NotNull(message = "Service name is required.") String name, Double price, String serviceUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.serviceUrl = serviceUrl;
    }

    public Service() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String pictureUrl) {
        this.serviceUrl = pictureUrl;
    }
}
