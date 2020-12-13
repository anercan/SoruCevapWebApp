package com.anercan.sorucevap.service;

import com.anercan.sorucevap.client.dto.CreateUserDto;
import com.anercan.sorucevap.client.resource.ServiceResult;
import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.dao.BaseRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractEntityService<User> implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    BaseRepository<User, Long> getRepository() {
        return userRepository;
    }

    public User getCurrentUser() {
        Long userId = getUserId();
        if (userId != null) {
            Optional<User> byId = userRepository.findById(userId);
            if (byId.isPresent()) {
                return byId.get();
            }
        }
        return null;
    }

    public ServiceResult<Optional<User>> getByMail(String mail) {
        if (!userRepository.findByMail(mail).isPresent()) {
            createFailResult();
        }
        log.info("User get by mail.Mail:{}", mail);
        return createServiceResult(userRepository.findByMail(mail));
    }

    public UserDetails loadUserByUsername(String usernameOrMail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameOrMail(usernameOrMail);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(usernameOrMail);
        }
        return new CustomUserDetail(user.get());
    }

    public UserDetails findByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user.get());
    }

    public ServiceResult<Boolean> createUser(CreateUserDto userDto) {
        Optional<User> userOpt = userRepository.findByUsernameOrMail(userDto.getUserName().toLowerCase());
        if (userOpt.isPresent()) {
            return createServiceResult(false, PropertyUtil.getStringValue("app.text.login.user.already.exist", "User Already Exist!"));
        }
        Optional<User> mailOtp = userRepository.findByUsernameOrMail(userDto.getMail().toLowerCase());
        if (userOpt.isPresent()) {
            return createServiceResult(false, PropertyUtil.getStringValue("app.text.login.user.already.exist", "User Already Exist!"));
        }
        try {
            User user = new User();
            user.setMail(userDto.getMail().toLowerCase()); //todo pattern eklenecek
            user.setPassword(userDto.getPassword());
            user.setUsername(userDto.getUserName().toLowerCase());
            user.setQuestionStatus(PropertyUtil.getIntegerValue("app.user.default.question.right", 5));
            //todo event eklenecek
            super.save(user);
            return new ServiceResult(Boolean.TRUE);
        } catch (Exception e) {
            log.info("User oluşturulma başarısız.UserDto:{} -- stacktrace:{}", userDto, e.getMessage());
            return createFailResult();
        }

    }


}
