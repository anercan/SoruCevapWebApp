package com.anercan.sorucevap.service;

import com.anercan.sorucevap.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByUserId(Long id);

    User createUser(User user);
}
