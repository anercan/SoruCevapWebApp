package com.anercan.sorucevap.web;

import com.anercan.sorucevap.constants.ErrorMessage;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@RequestMapping("/sign-up")
@Controller
public class UserSignUpController {

        @Autowired
        private Environment env;

        @Autowired
        UserRepository userRepository;

        @RequestMapping("/showform")
        public ModelAndView showSignUpForm() {

            ModelAndView maw = new ModelAndView();
            maw.addObject("user", new User());
            maw.setViewName("sign-up");

            return maw;
        }

        @RequestMapping("/processform")
        public ModelAndView actionSignUp(@Valid @ModelAttribute("user") User user, BindingResult theBr) {
            Date date = new Date();
            ModelAndView maw = new ModelAndView();

            if(theBr.hasErrors()) {maw.setViewName("sign-up"); return maw; }//validation control

            if(userRepository.findByMail(user.getMail())!=null) {

                maw.addObject("exist_mail", ErrorMessage.existMessageMail);
                maw.setViewName("sign-up");

                return maw;
            }

            if(userRepository.findByUsername(user.getUsername())!=null) {

                maw.addObject("exist_username",ErrorMessage.existMessageUsername);
                maw.setViewName("sign-up");

                return maw;
            }
            user.setDate(date);
            user.setQuestionStatus(Integer.parseInt(env.getProperty("appconstant.question-status")));
            userRepository.save(user);
            maw.setViewName("index");

            return maw;
        }
    }

