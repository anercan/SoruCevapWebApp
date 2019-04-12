package com.anercan.sorucevap.web;

import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.model.Question;
import com.anercan.sorucevap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HomePageController {

    @Autowired
    QuestionRepository questionRepository;

    @RequestMapping("/")
    ModelAndView receiveHomePage(ModelAndView maw){

        List<Question> questionList = questionRepository.findAll();
        maw.addObject(questionList);
        maw.addObject(new User());
        maw.setViewName("index");

        return maw;
    }



}
