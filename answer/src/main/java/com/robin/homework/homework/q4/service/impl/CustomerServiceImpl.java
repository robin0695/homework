package com.robin.homework.homework.q4.service.impl;

import com.robin.homework.homework.q4.module.CustomerPo;
import com.robin.homework.homework.q4.repository.CustomerRepository;
import com.robin.homework.homework.q4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  @Override
  @Transactional
  public CustomerPo saveCustomer(CustomerPo customerPo) {
    Optional<CustomerPo> result = customerRepository.save(customerPo);
    return result.isEmpty()? null : result.get();
  }

  @Override
  public CustomerPo findCustomerById(int id) {
    Optional<CustomerPo> result = customerRepository.findById(id);
    return result.isEmpty()? null : result.get();
  }
}
