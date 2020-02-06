package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.dto.UserDto;
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
            new JsonResponse<>().setCode(-1);
        }
        logger.info("User mail ile getirildi.Mail:{}",mail);
        return new JsonResponse<>(userRepository.findByMail(mail));
    }

    public JsonResponse<Optional<User>> getByUserId(Long id){
        if (!userRepository.findById(id).isPresent()) {
            new JsonResponse<>().setCode(-1);
        }
        logger.info("User idsiyle getirildi.Id:{}",id);
        return new JsonResponse<>(userRepository.findById(id));
    }

    @Override
    public JsonResponse<Optional<User>> getByUserName(String userName) {
        if (!userRepository.findByUsername(userName).isPresent()) {
            new JsonResponse<>().setCode(-1);
        }
        logger.info("Username ile getirildi.UserName:{}",userName);
        return new JsonResponse<>(userRepository.findByUsername(userName));
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
