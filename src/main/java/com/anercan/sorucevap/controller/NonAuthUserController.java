package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.controller.auth.BaseController;
import com.anercan.sorucevap.dto.UserDto;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class NonAuthUserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping("/username/{userName}")
    JsonResponse<Optional<User>> getByUsername(@PathVariable String userName) {
        return createJsonResponse(userService.getByUserName(userName));
    }

    @GetMapping("/mail/{mail}")
    JsonResponse<Optional<User>> getByMail(@PathVariable String mail) {
        return createJsonResponse(userService.getByMail(mail));
    }

    @PostMapping("/create")
    JsonResponse<Boolean> createUser(@RequestBody UserDto userDto) {
        return createJsonResponse(userService.createUser(userDto));
    }
}



