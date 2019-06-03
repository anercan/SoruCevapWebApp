package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUserId(Long id){
        logger.info("User idsiyle getirildi.Id:{}",id);
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setAnswerCount(0);
        user.setDate(date);
        user.setQuestionCount(0);
        user.setQuestionStatus(Integer.parseInt(env.getProperty("appconstant.question-status")));
        logger.info("Yeni User oluşturuldu.User:{}",user);
        return userRepository.save(user);
    }
}
