package com.robin.homework.q4.repository;

import com.robin.homework.q4.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
