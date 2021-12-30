package com.robin.homework.q4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Subscription {

    @EmbeddedId
    @JsonIgnore
    private SubscriptionKey pk;

    @Column(nullable = false) private Integer timeInDay;

    public Subscription() {
        super();
    }

    public Subscription(Customer customer, Service service, Integer timeInDay) {
        pk = new SubscriptionKey();
        pk.setCustomer(customer);
        pk.setService(service);
        this.timeInDay = timeInDay;
    }

    @Transient
    public Service getService() {
        return this.pk.getService();
    }

    @Transient
    public Double getTotalPrice() {
        return getService().getPrice() * getTimeInDay();
    }

    public SubscriptionKey getPk() {
        return pk;
    }

    public void setPk(SubscriptionKey pk) {
        this.pk = pk;
    }

    public Integer getTimeInDay() {
        return timeInDay;
    }

    public void setTimeInDay(Integer quantity) {
        this.timeInDay = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Subscription other = (Subscription) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }

        return true;
    }
}
