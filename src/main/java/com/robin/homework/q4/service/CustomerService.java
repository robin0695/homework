package com.robin.homework.q4.service;

import com.robin.homework.q4.model.Customer;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface CustomerService {

    @NotNull Iterable<Customer> getAllCustomers();

    Customer create(@NotNull(message = "The customer cannot be null.") @Valid Customer customer);

    void update(@NotNull(message = "The customer cannot be null.") @Valid Customer customer);
}
