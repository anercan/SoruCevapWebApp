package com.anercan.sorucevap.service;

import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.dao.BaseRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.client.dto.UserDto;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.client.resource.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractEntityService<User> {

    @Autowired
    UserRepository userRepository;

    @Override
    BaseRepository<User, Long> getRepository() {
        return userRepository;
    }

    public ServiceResult<Optional<User>> getByMail(String mail) {
        if (!userRepository.findByMail(mail).isPresent()) {
            createFailResult();
        }
        log.info("User get by mail.Mail:{}", mail);
        return createServiceResult(userRepository.findByMail(mail));
    }


    public ServiceResult<Optional<User>> getByUserName(String userName) {
        if (!userRepository.findByUsername(userName).isPresent()) {
            createFailResult();
        }
        log.info("User getbyUsername.UserName:{}", userName);
        return createServiceResult(userRepository.findByUsername(userName));
    }


    public ServiceResult<Boolean> createUser(UserDto userDto) {
        Optional<User> userOpt = userRepository.findByUsername(userDto.getUsername().toLowerCase());
        if (userOpt.isPresent()) {
            return createServiceResult(false, PropertyUtil.getStringValue("app.text.login.user.already.exist", "User Already Exist!"));
        }
        Optional<User> userOptMail = userRepository.findByMail(userDto.getMail());
        if (userOptMail.isPresent()) {
            return createServiceResult(false, PropertyUtil.getStringValue("app.text.login.mail.already.exist", "Mail Already Exist!"));
        }
        try {
            User user = new User();
            user.setMail(userDto.getMail().toLowerCase()); //todo pattern eklenecek
            user.setPassword(userDto.getPassword());
            user.setUsername(userDto.getUsername().toLowerCase());
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
