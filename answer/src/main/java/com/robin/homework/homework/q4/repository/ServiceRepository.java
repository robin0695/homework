package com.robin.homework.homework.q4.repository;

import com.robin.homework.homework.q4.module.CustomerPo;
import com.robin.homework.homework.q4.module.ServicePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public class ServiceRepository {

  @Autowired
  private EntityManager entityManager;

  public Optional<ServicePo> findById(Integer id) {
    ServicePo servicePo = entityManager.find(ServicePo.class, id);
    return servicePo != null ? Optional.of(servicePo) : Optional.empty();
  }

}
