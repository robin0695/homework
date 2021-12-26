package com.robin.homework.homework.q4.service;

import com.robin.homework.homework.q4.module.CustomerPo;

public interface CustomerService {
  CustomerPo saveCustomer(CustomerPo customerPo);
  CustomerPo findCustomerById(int id);

}
