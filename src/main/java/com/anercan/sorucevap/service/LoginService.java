package com.anercan.sorucevap.service;


import com.anercan.sorucevap.client.dto.UserDto;
import com.anercan.sorucevap.client.resource.ServiceResult;
import com.anercan.sorucevap.config.JwtUtil;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService extends BaseService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public ServiceResult<String> login(UserDto dto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserNameOrMail(), dto.getPassword()));
        } catch (BadCredentialsException e) {
            log.warn("Bad Credentials on Login Operation user:{}", dto.getUserNameOrMail());
            return createFailResult();
        }
        Optional<User> byUsernameOrMail = userRepository.findByUsernameOrMail(dto.getUserNameOrMail());
        if (byUsernameOrMail.isPresent()) {
            return createServiceResult(JwtUtil.createJWT(byUsernameOrMail.get()));
        }
        return createFailResult();
    }

    public ServiceResult<Boolean> logout() {
        SecurityContextHolder.getContext().setAuthentication(null); // Spring security ayarlarından da yapılabilir
        return createServiceResult(true);
    }

}
