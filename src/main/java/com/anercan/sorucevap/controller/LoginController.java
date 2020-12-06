package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.client.dto.CreateUserDto;
import com.anercan.sorucevap.client.dto.UserDto;
import com.anercan.sorucevap.client.resource.JsonResponse;
import com.anercan.sorucevap.service.LoginService;
import com.anercan.sorucevap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @PostMapping("/user-logout")
    JsonResponse<Boolean> logout() {
        return createJsonResponse(loginService.logout());
    }

    @PostMapping("/user-login")
    JsonResponse<UserDto> login(@RequestBody UserDto userDto) {
        return createJsonResponse(loginService.login(userDto));
    }

    @PostMapping("/sign-up")
    JsonResponse<Boolean> createUser(@RequestBody CreateUserDto userDto) {
        return createJsonResponse(userService.createUser(userDto));
    }


}
