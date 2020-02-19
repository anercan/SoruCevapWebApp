package com.anercan.sorucevap.service.impl;


import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.config.SecurityConfig;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.dto.LoginDto;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.resource.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class LoginService extends BaseService {

    @Autowired
    UserRepository userRepository;

    public JsonResponse<Boolean> login(LoginDto dto, HttpServletResponse response) {
        Optional<User> userOpt = userRepository.findByUsername(dto.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(dto.getPassword())) {
                Cookie cookie = new Cookie("token", SecurityConfig.createJWT(user.getId().toString()));
                cookie.setMaxAge(PropertyUtil.getIntegerValue("app.login.cookie.max.age", 3600)); //1 hour
                response.addCookie(cookie);
            } else {
                return createFailResult(PropertyUtil.getStringValue("app.login.fail.text.password"));
            }
        } else {
            return createFailResult(PropertyUtil.getStringValue("app.login.fail.text.usernotfound"));
        }
        return createFailResult();
    }

    public JsonResponse<Boolean> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new JsonResponse(true);
    }

    }
