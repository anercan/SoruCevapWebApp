package com.anercan.sorucevap.service;

import com.anercan.sorucevap.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByMail(String mail);

    Optional<User> getByUserId(Long id);

    Optional<User> getByUserName(String userName);

    User createUser(User user);
}
