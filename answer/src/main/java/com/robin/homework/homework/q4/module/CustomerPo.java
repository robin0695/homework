package com.robin.homework.homework.q4.module;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HOMEWORK_CUSTOMER_TBL")
public class CustomerPo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
  private Set<SubscriptionPo> subscriptions = new HashSet<>();

  public void addSubscription(SubscriptionPo subscriptionPo) {
    this.subscriptions.add(subscriptionPo);
    subscriptionPo.setCustomer(this);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<SubscriptionPo> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(Set<SubscriptionPo> subscriptions) {
    this.subscriptions = subscriptions;
  }

  @Override
  public String toString() {
    return "CustomerPo{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
