package com.robin.homework.homework.q4.controller;

import com.robin.homework.homework.q4.module.CustomerPo;
import com.robin.homework.homework.q4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("customer")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @GetMapping
  public CustomerPo getCustomer() {
    return customerService.findCustomerById(1);
  }

  @PostMapping
  public CustomerPo saveCustomer(@RequestBody CustomerPo customerPo) {
    return customerService.saveCustomer(customerPo);
  }
}
