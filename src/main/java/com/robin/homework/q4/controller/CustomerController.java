package com.robin.homework.q4.controller;

import com.robin.homework.q4.dto.SubscriptionDto;
import com.robin.homework.q4.exception.ResourceNotFoundException;
import com.robin.homework.q4.model.Customer;
import com.robin.homework.q4.model.Subscription;
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

    public static class CustomerForm {

        private List<SubscriptionDto> Subscriptions;

        public List<SubscriptionDto> getSubscriptions() {
            return Subscriptions;
        }

        public void setSubscriptions(List<SubscriptionDto> subscriptions) {
            this.Subscriptions = subscriptions;
        }
    }
}
