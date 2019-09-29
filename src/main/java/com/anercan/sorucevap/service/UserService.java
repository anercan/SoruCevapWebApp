package com.anercan.sorucevap.service;

import com.anercan.sorucevap.entity.JsonResponse;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.entity.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<User> getByMail(String mail);

    Optional<User> getByUserId(Long id);

    Optional<User> getByUserName(String userName);

    JsonResponse<Boolean> createUser(UserDto userdto);
}
