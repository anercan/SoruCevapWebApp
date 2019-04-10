package com.anercan.sorucevap.web;

import com.anercan.sorucevap.constants.ErrorMessage;
import com.anercan.sorucevap.dao.UserRepository;
import com.anercan.sorucevap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class UserLogInController {


    @Autowired
    UserRepository userRepository;

    @RequestMapping("/showform")
    ModelAndView showLogInForm(ModelAndView maw){

        maw.addObject("user",new User());
        maw.setViewName("login");
        return maw;
    }

    @RequestMapping("/processform")
    public ModelAndView actionSignUp(@Valid @ModelAttribute("user") User user, BindingResult theBr) {

        ModelAndView maw = new ModelAndView();

        if(theBr.hasErrors()) {
            maw.addObject("non_exist_mail", ErrorMessage.nonExistMessageMail);
            maw.setViewName("login");
            return maw;
        }

        if(userRepository.findByMail(user.getMail()).getPassword().equals(user.getPassword())){
            maw.setViewName("profile");
            maw.addObject("user",userRepository.findByMail(user.getMail()));
            return maw;
        }
        else{
            maw.addObject("non_exist_mail", ErrorMessage.nonExistMessageMail);
            maw.setViewName("login");

            return maw;
        }
    }
}
