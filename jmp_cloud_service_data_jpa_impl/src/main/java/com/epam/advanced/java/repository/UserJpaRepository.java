package com.epam.advanced.java.repository;

import com.epam.advanced.java.domain.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
