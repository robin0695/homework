package com.robin.homework.q4.service;

import com.robin.homework.q4.model.Customer;
import com.robin.homework.q4.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Iterable<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer create(Customer customer) {
        customer.setDateCreated(LocalDate.now());

        return this.customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        return customerOptional.orElse(null);
    }

}
