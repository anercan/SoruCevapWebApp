package com.anercan.sorucevap.web;

import com.anercan.sorucevap.constants.ErrorMessage;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.model.Question;
import com.anercan.sorucevap.model.User;
import com.anercan.sorucevap.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class UserLogInController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileService profileService;

    @RequestMapping("/showform")
    ModelAndView showLogInForm(ModelAndView maw){

        maw.addObject("user",new User());
        maw.setViewName("login");
        return maw;
    }

    @RequestMapping("/processform")
    public ModelAndView actionSignUp(@Valid @ModelAttribute("user") User user, BindingResult theBr,HttpServletRequest request) {

        ModelAndView maw = new ModelAndView();


        if(theBr.hasErrors()) {
            maw.addObject("non_exist_mail", ErrorMessage.nonExistMessageMail);
            maw.setViewName("login");
            return maw;
        }

        if(userRepository.findByMail(user.getMail()).getPassword().equals(user.getPassword())){
            maw.addObject(new Question());
            maw.setViewName("profile");
            maw.addObject("user",userRepository.findByMail(user.getMail()));

            HttpSession session = request.getSession();
            session.setAttribute("mail",user.getMail());

            user = userRepository.findByMail(user.getMail());

            maw.addObject("answeredQuestionList",profileService.getAnsweredQuestions(user));
            maw.addObject("nonAnsweredQuestionList",profileService.getNotAnsweredQuestions(user));

            return maw;
        }
        else{
            maw.addObject("non_exist_mail", ErrorMessage.nonExistMessageMail);
            maw.setViewName("login");

            return maw;
        }
    }
}
