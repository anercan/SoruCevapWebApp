package com.anercan.sorucevap.service;

import com.anercan.sorucevap.SorucevapApplication;

import com.anercan.sorucevap.dao.AnswerRepository;
import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BaseService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    protected Environment env;

    protected static final Logger logger = LoggerFactory.getLogger(SorucevapApplication.class);

    Date date = new Date();
}
