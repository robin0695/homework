package com.robin.homework.homework.q4.module;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HOMEWORK_SERVICE_SUBSCRIPTION_TBL")
public class SubscriptionPo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  @JsonIgnore
  CustomerPo customer;

  @ManyToOne
  @JoinColumn(name = "service_id")
  @JsonIgnore
  ServicePo service;

  @Column(name = "subscribed_at")
  LocalDateTime subscribedAt;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CustomerPo getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerPo customer) {
    this.customer = customer;
  }

  public ServicePo getService() {
    return service;
  }

  public void setService(ServicePo service) {
    this.service = service;
  }

  public LocalDateTime getSubscribedAt() {
    return subscribedAt;
  }

  public void setSubscribedAt(LocalDateTime subscribedAt) {
    this.subscribedAt = subscribedAt;
  }
}
