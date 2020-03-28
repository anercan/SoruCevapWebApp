package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.dto.UserDto;
import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.service.UserService;
import com.anercan.sorucevap.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class NonAuthUserController {

    @Autowired
    UserService userService;

    @GetMapping("/username/{userName}")
    JsonResponse<Optional<User>> getByUsername(@PathVariable String userName) {
        return userService.getByUserName(userName);
    }

    @GetMapping("/mail/{mail}")
    JsonResponse<Optional<User>> getByMail(@PathVariable String mail) {
        return userService.getByMail(mail);
    }

    @PostMapping("/create")
    JsonResponse<Boolean> createUser(@RequestBody UserDto userDto) {

        return userService.createUser(userDto);
    }




}



