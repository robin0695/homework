package com.robin.homework.q4.controller;

import com.robin.homework.q4.dto.SubscriptionDto;
import com.robin.homework.q4.exception.ResourceNotFoundException;
import com.robin.homework.q4.model.Customer;
import com.robin.homework.q4.model.Subscription;
import com.robin.homework.q4.model.SubscriptionKey;
import com.robin.homework.q4.model.SubscriptionStatus;
import com.robin.homework.q4.service.CustomerService;
import com.robin.homework.q4.service.ServiceService;
import com.robin.homework.q4.service.SubscriptionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    ServiceService serviceService;
    CustomerService customerService;
    SubscriptionService subscriptionService;

    public CustomerController(ServiceService serviceService, CustomerService customerService, SubscriptionService subscriptionService) {
        this.serviceService = serviceService;
        this.customerService = customerService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Customer> list() {
        return this.customerService.getAllCustomers();
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody CustomerForm form) {
        List<SubscriptionDto> formDtos = form.getSubscriptions();
        validateServicesExistence(formDtos);
        Customer customer = new Customer();
        customer.setStatus(SubscriptionStatus.DONE.name());
        customer = this.customerService.create(customer);

        List<Subscription> subscriptions = new ArrayList<>();
        for (SubscriptionDto dto : formDtos) {
            subscriptions.add(subscriptionService.create(new Subscription(customer, serviceService.getService(dto
              .getService()
              .getId()), dto.getQuantity())));
        }

        customer.setSubscriptions(subscriptions);

        this.customerService.update(customer);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/customers/{id}")
          .buildAndExpand(customer.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> addSubscription(@RequestBody CustomerForm form) {
        List<SubscriptionDto> formDtos = form.getSubscriptions();
        validateServicesExistence(formDtos);
        validateCustomerExistence(form.getId());
        Customer customer = customerService.getCustomerById(form.getId());
        List<Subscription> subscriptions = new ArrayList<>();
        for (SubscriptionDto dto : formDtos) {
            subscriptions.add(subscriptionService.create(new Subscription(customer, serviceService.getService(dto
                .getService()
                .getId()), dto.getQuantity())));
        }

        customer.getSubscriptions().addAll(subscriptions);
        String uri = ServletUriComponentsBuilder
            .fromCurrentServletMapping()
            .path("/customers/{id}")
            .buildAndExpand(customer.getId())
            .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(customer, headers, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Customer> deleteSubscription(@RequestBody CustomerForm form) {
        List<SubscriptionDto> formDtos = form.getSubscriptions();
        validateServicesExistence(formDtos);
        validateCustomerExistence(form.getId());
        Customer customer = customerService.getCustomerById(form.getId());
        for (SubscriptionDto dto : formDtos) {
            subscriptionService.delete(new SubscriptionKey(customer, serviceService.getService(dto.getService().getId())));
            customer.getSubscriptions().removeIf(e -> e.getService().getId().equals(dto.getService().getId()));
        }

        String uri = ServletUriComponentsBuilder
            .fromCurrentServletMapping()
            .path("/customers/{id}")
            .buildAndExpand(customer.getId())
            .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(customer, headers, HttpStatus.OK);
    }

    private void validateServicesExistence(List<SubscriptionDto>  subscriptionDtos) {
        List<SubscriptionDto> list = subscriptionDtos
          .stream()
          .filter(op -> Objects.isNull(serviceService.getService(op
            .getService()
            .getId())))
          .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Service not found");
        }
    }

    private void validateCustomerExistence(Long id) {
      Customer result = customerService.getCustomerById(id);
        if (result == null) {
            new ResourceNotFoundException("Customer not found");
        }
    }

    public static class CustomerForm {

        private Long id;

        private List<SubscriptionDto> Subscriptions;

        public List<SubscriptionDto> getSubscriptions() {
            return Subscriptions;
        }

        public void setSubscriptions(List<SubscriptionDto> subscriptions) {
            this.Subscriptions = subscriptions;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
