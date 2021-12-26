package com.robin.homework.homework.q4.repository;

import com.robin.homework.homework.q4.module.CustomerPo;
import com.robin.homework.homework.q4.module.ServicePo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServicePo, Integer> {
}
