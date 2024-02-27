package com.epam.advanced.java.repository;

import com.epam.advanced.java.domain.bo.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Long> {

}
