package com.anercan.sorucevap.web;

import com.anercan.sorucevap.constants.ErrorMessage;
import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.model.Question;
import com.anercan.sorucevap.model.User;
import com.anercan.sorucevap.service.ProfileService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileService profileService;

    @RequestMapping("/ask")
    public ModelAndView askQuestionHandler(@Valid @ModelAttribute("question") Question question , BindingResult theBr, HttpServletRequest request){

        Date date = new Date();
        ModelAndView maw = new ModelAndView();
        HttpSession session = request.getSession();

        User user = userRepository.findByMail(session.getAttribute("mail").toString());

        maw.addObject("answeredQuestionList",profileService.getAnsweredQuestions(user));
        maw.addObject("nonAnsweredQuestionList",profileService.getNotAnsweredQuestions(user));

        /*
        if(theBr.hasErrors()) {
            maw.addObject("answeredQuestionList",profileService.getAnsweredQuestions(user));
            maw.addObject("nonAnsweredQuestionList",profileService.getAnsweredQuestions(user));
            maw.addObject(user);
            maw.addObject(new Question());
            maw.setViewName("profile");
        }*/

        if(user.getQuestionStatus()>0 ) {

            user.setQuestionStatus(user.getQuestionStatus() - 1);  // soru soruldugunda soru statusunden eksilme
            user.setQuestionCount(user.getQuestionCount() + 1);    // user soru sayisinda artis
            question.setUser(user);
            question.setDate(date);

            userRepository.save(user);
            questionRepository.save(question);

            maw.addObject(user);
            maw.addObject(new Question());
            maw.setViewName("profile");

            return maw;
        }
        else {
            maw.addObject("question_right", ErrorMessage.noQuestionRight);
            maw.addObject(user);
            maw.addObject(new Question());
            maw.setViewName("profile");
            return  maw;
        }
    }
}