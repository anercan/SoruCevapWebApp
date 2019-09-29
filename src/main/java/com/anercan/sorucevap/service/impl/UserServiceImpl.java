package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.entity.JsonResponse;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.entity.dto.UserDto;
import com.anercan.sorucevap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getByMail(String mail) {
        logger.info("User mail ile getirildi.Mail:{}",mail);
        return userRepository.findByMail(mail);
    }

    public Optional<User> getByUserId(Long id){
        logger.info("User idsiyle getirildi.Id:{}",id);
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        logger.info("Username ile getirildi.UserName:{}",userName);
        return userRepository.findByUsername(userName);
    }

    @Override
    public JsonResponse<Boolean> createUser(UserDto userDto) {
        JsonResponse response = new JsonResponse();
        User user = new User();
        user.setDate(date);
        user.setMail(userDto.getMail());
        user.setPassword(userDto.getPassword());
        user.setQuestionStatus(Integer.parseInt(env.getProperty("appconstant.question-status")));
        userRepository.save(user);
        logger.info("Yeni User oluşturuldu.User:{}",user);
        response.setValue(true);
        response.setCode(0);
        return response;
    }
}
