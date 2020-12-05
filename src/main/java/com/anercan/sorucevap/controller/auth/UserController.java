package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.controller.BaseController;
import com.anercan.sorucevap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user-settings")
@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService;


    //todo user pasife cekme

}



