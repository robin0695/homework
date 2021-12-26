package com.robin.homework.homework.q4.repository;

import com.robin.homework.homework.q4.module.CustomerPo;
import com.robin.homework.homework.q4.module.SubscriptionPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public class CustomerSubscriptionRepository {

  @Autowired
  private EntityManager entityManager;

  public Optional<SubscriptionPo> save(SubscriptionPo subscriptionPo) {
    try {
      entityManager.persist(subscriptionPo);
      return Optional.of(subscriptionPo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

}
