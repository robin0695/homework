package com.robin.homework.q4.service;

import com.robin.homework.q4.model.Subscription;
import com.robin.homework.q4.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription create(Subscription subscription) {
        return this.subscriptionRepository.save(subscription);
    }
}
