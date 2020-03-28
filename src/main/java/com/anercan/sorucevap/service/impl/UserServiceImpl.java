package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.dto.UserDto;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public JsonResponse<Optional<User>> getByMail(String mail) {
        if (!userRepository.findByMail(mail).isPresent()) {
            createFailResult();
        }
        logger.info("User get by mail.Mail:{}", mail);
        return new JsonResponse<>(userRepository.findByMail(mail));
    }

    public JsonResponse<Optional<User>> getByUserId(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            createFailResult();
        }
        logger.info("User get by id.Id:{}", id);
        return new JsonResponse<>(userRepository.findById(id));
    }

    @Override
    public JsonResponse<Optional<User>> getByUserName(String userName) {
        if (!userRepository.findByUsername(userName).isPresent()) {
            createFailResult();
        }
        logger.info("User getbyUsername.UserName:{}", userName);
        return new JsonResponse<>(userRepository.findByUsername(userName));
    }

    @Override
    public JsonResponse<Boolean> createUser(UserDto userDto) {
        Optional<User> userOpt = userRepository.findByUsername(userDto.getUsername().toLowerCase());
        if (userOpt.isPresent()){
            return new JsonResponse<>(false,PropertyUtil.getStringValue("app.text.login.user.already.exist","User Already Exist!"));
        }
        Optional<User> userOptMail = userRepository.findByMail(userDto.getMail());
        if (userOptMail.isPresent()){
            return new JsonResponse<>(false,PropertyUtil.getStringValue("app.text.login.mail.already.exist","Mail Already Exist!"));
        }
        try {
            User user = new User();
            user.setDate(date);
            user.setMail(userDto.getMail().toLowerCase()); //todo pattern eklenecek
            user.setPassword(userDto.getPassword());
            user.setUsername(userDto.getUsername().toLowerCase());
            user.setQuestionStatus(PropertyUtil.getIntegerValue("app.user.default.question.right",5));
            //todo event eklenecek
            userRepository.save(user);
            logger.info("New User created:", user);

            return new JsonResponse(Boolean.TRUE);
        } catch (Exception e) {
            logger.info("User oluşturulma başarısız.UserDto:{} -- stacktrace:{}", userDto,e.getMessage());
            return createFailResult();
        }

    }
}
