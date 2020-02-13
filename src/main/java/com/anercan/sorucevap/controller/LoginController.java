package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.dto.DashboardDto;
import com.anercan.sorucevap.dto.LoginDto;
import com.anercan.sorucevap.resource.DashboardResource;
import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    JsonResponse<Boolean> checkLogin(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        return loginService.checkLogin(loginDto,response);
    }

}
