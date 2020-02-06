package com.anercan.sorucevap.service;

import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.dto.UserDto;

import java.util.Optional;

public interface UserService {

    JsonResponse<Optional<User>> getByMail(String mail);

    JsonResponse<Optional<User>> getByUserId(Long id);

    JsonResponse<Optional<User>> getByUserName(String userName);

    JsonResponse<Boolean> createUser(UserDto userdto);

    //todo silinen userların question ve cevaplarda anonim yazması
}
