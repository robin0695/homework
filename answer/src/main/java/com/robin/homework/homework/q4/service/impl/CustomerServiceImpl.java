package com.robin.homework.homework.q4.service.impl;

import com.robin.homework.homework.q4.module.CustomerPo;
import com.robin.homework.homework.q4.module.SubscriptionPo;
import com.robin.homework.homework.q4.repository.CustomerRepository;
import com.robin.homework.homework.q4.repository.CustomerSubscriptionRepository;
import com.robin.homework.homework.q4.repository.ServiceRepository;
import com.robin.homework.homework.q4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  ServiceRepository serviceRepository;

  @Autowired
  CustomerSubscriptionRepository customerSubscriptionRepository;

  @Override
  @Transactional
  public CustomerPo saveCustomer(CustomerPo customerPo) {
    CustomerPo customerPo1 = new CustomerPo();
    customerPo1.setName(customerPo.getName());
    Optional<CustomerPo> result = customerRepository.save(customerPo1);

    for(SubscriptionPo subscriptionPo: customerPo.getSubscriptions()){
      if(serviceRepository.findById(subscriptionPo.getService().getId()).isPresent()) {

        if(result.isPresent()){
          SubscriptionPo subscriptionPo1 = new SubscriptionPo();
          subscriptionPo1.setCustomer(result.get());
          subscriptionPo1.setService(serviceRepository.findById(subscriptionPo.getService().getId()).get());
          customerSubscriptionRepository.save(subscriptionPo1);
        }
      }
    }
    return result.isEmpty()? null : result.get();
  }

  @Override
  public CustomerPo findCustomerById(int id) {
    Optional<CustomerPo> result = customerRepository.findById(id);
    return result.isEmpty()? null : result.get();
  }
}
