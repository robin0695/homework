package com.robin.homework.homework.q4.repository;

import com.robin.homework.homework.q4.module.CustomerPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public class CustomerRepository {

  @Autowired
  private EntityManager entityManager;

  public Optional<CustomerPo> save(CustomerPo customerPo) {
    try {
      entityManager.persist(customerPo);
      return Optional.of(customerPo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public Optional<CustomerPo> findById(Integer id) {
    CustomerPo customerPo = entityManager.find(CustomerPo.class, id);
    return customerPo != null ? Optional.of(customerPo) : Optional.empty();
  }

}
