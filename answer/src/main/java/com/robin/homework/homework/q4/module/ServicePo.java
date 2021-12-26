package com.robin.homework.homework.q4.module;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "HOMEWORK_SERVICE_TBL")
public class ServicePo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "code")
  private String code;

  @OneToMany(mappedBy = "service", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<SubscriptionPo> subscriptions;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Set<SubscriptionPo> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(Set<SubscriptionPo> subscriptions) {
    this.subscriptions = subscriptions;
  }

  @Override
  public String toString() {
    return "ServicePo{" +
      "id=" + id +
      ", code='" + code + '\'' +
      '}';
  }
}
