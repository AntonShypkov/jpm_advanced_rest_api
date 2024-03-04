package com.epam.advanced.java.repository;

import com.epam.advanced.java.bo.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {

    private Map<Long, User> usersStorage = new HashMap<>();

    public User create(User user) {
        Long userId = user.getId();
        usersStorage.put(userId, user);
        return usersStorage.get(userId);
    }

    public User update(User user) {
        Long userId = user.getId();
        usersStorage.put(userId, user);
        return usersStorage.get(userId);
    }

    public void delete(Long userId) {
        usersStorage.remove(userId);
    }

    public User findById(Long userId) {
        return usersStorage.get(userId);
    }

    public Collection<User> findAll() {
        return usersStorage.values();
    }
}
