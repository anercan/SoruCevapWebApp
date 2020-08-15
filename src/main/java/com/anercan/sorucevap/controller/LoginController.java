package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.controller.auth.BaseController;
import com.anercan.sorucevap.client.dto.UserDto;
import com.anercan.sorucevap.client.resource.JsonResponse;
import com.anercan.sorucevap.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    @PostMapping("/logout")
    JsonResponse<Boolean> logout(UserDto userDto, HttpServletResponse response) {
        return createJsonResponse(loginService.logout(userDto, response));
    }

    @PostMapping("/login")
    JsonResponse<UserDto> login(@RequestBody UserDto userDto, HttpServletResponse response) {
        return createJsonResponse(loginService.login(userDto, response));
    }


}
