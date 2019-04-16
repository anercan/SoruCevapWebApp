package com.anercan.sorucevap.web;

import com.anercan.sorucevap.constants.ErrorMessage;
import com.anercan.sorucevap.dao.QuestionRepository;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.model.Question;
import com.anercan.sorucevap.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/ask")
    public ModelAndView askQuestionHandler(@ModelAttribute("question") Question question , HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();

        Date date = new Date();
        ModelAndView maw = new ModelAndView();

        User user = userRepository.findByMail(session.getAttribute("mail").toString());

        if(user.getQuestionStatus()>0) {

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
