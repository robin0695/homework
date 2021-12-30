package com.robin.homework.q4.repository;

import com.robin.homework.q4.model.Subscription;
import com.robin.homework.q4.model.SubscriptionKey;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, SubscriptionKey> {
}
