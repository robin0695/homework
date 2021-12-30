package com.robin.homework.q4.dto;

import com.robin.homework.q4.model.Service;

public class SubscriptionDto {

    private Service service;
    private Integer quantity;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
