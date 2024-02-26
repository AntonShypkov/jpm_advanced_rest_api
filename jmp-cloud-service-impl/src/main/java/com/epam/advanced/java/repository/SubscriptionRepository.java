package com.epam.advanced.java.repository;

import com.epam.advanced.java.bo.Subscription;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class SubscriptionRepository {

    private Map<Long, Subscription> subscriptionStorage = new HashMap<>();

    public Subscription create(Subscription subscription) {
        Long subscriptionId = subscription.getId();
        subscriptionStorage.put(subscriptionId, subscription);
        return subscriptionStorage.get(subscriptionId);
    }

    public Subscription update(Subscription subscription) {
        Long subscriptionId = subscription.getId();
        subscriptionStorage.put(subscriptionId, subscription);
        return subscriptionStorage.get(subscriptionId);
    }

    public void delete(Long subscriptionId) {
        subscriptionStorage.remove(subscriptionId);
    }

    public Subscription findById(Long subscriptionId) {
        return subscriptionStorage.get(subscriptionId);
    }

    public Collection<Subscription> findAll() {
        return subscriptionStorage.values();
    }
}
