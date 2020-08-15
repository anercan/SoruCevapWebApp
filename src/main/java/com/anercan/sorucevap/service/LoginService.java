package com.anercan.sorucevap.service;


import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.config.SecurityConfig;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.client.dto.UserDto;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.client.resource.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Service
public class LoginService extends BaseService {

    @Autowired
    UserRepository userRepository;

    public ServiceResult<UserDto> login(UserDto dto, HttpServletResponse response) {
        UserDto userResponse = new UserDto();
        Optional<User> userOpt = userRepository.findByUsername(dto.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(dto.getPassword())) {
                Cookie cookie = new Cookie("token", SecurityConfig.createJWT(user.getId().toString()));
                cookie.setMaxAge(PropertyUtil.getIntegerValue("app.login.cookie.max.age", 3600)); //1 hour
                response.addCookie(cookie);
                userResponse.setId(user.getId());
                userResponse.setUsername(user.getUsername());
            } else {
                return createFailResult(); //PropertyUtil.getStringValue("app.login.fail.text.password")
            }
        } else {
            return createFailResult(); //PropertyUtil.getStringValue("app.login.fail.text.password")
        }
        return createServiceResult(userResponse);
    }

    public boolean isLogin(HttpServletRequest request) {
        Cookie token = Arrays.stream(request.getCookies()).
                filter(cookie -> cookie.getName().equals("token")).findFirst().orElse(null);
        return token != null && SecurityConfig.checkJWT(token.getValue());
    }

    public ServiceResult<Boolean> logout(UserDto userDto, HttpServletResponse response) {
        try {
            Cookie cookie = new Cookie("token", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return createServiceResult(Boolean.TRUE);
        } catch (Exception e) {
            log.info("Logout error:user{} stackTrace:{}", userDto.getId(), e.getMessage());
            return createServiceResult(Boolean.TRUE);
        }
    }

}
