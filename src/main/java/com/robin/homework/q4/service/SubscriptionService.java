package com.robin.homework.q4.service;

import com.robin.homework.q4.model.Subscription;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface SubscriptionService {
    Subscription create(@NotNull(message = "The services for customer cannot be null.") @Valid Subscription subscription);
}
