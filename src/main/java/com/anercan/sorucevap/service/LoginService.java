package com.anercan.sorucevap.service;


import com.anercan.sorucevap.client.resource.UserResource;
import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.config.JwtUtil;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.client.dto.UserDto;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.client.resource.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Service
public class LoginService extends BaseService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public ServiceResult<UserResource> login(UserDto dto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserNameOrMail(), dto.getPassword()));
       return null;
    }

    public boolean isLogin(HttpServletRequest request) {
        Cookie token = Arrays.stream(request.getCookies()).
                filter(cookie -> cookie.getName().equals("token")).findFirst().orElse(null);
        return token != null && JwtUtil.checkJWT(token.getValue());
    }

    public ServiceResult<Boolean> logout(UserDto userDto, HttpServletResponse response) {
        return null;
    }

}
