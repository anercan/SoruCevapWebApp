package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/auth/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/username/{userName}")
    Optional<User> getByUsername(@PathVariable String userName){
        return userService.getByUserName(userName);
    }

    @GetMapping("/mail/{mail}")
    Optional<User> getByMail(@PathVariable String mail){
        return userService.getByMail(mail);
    }

    @GetMapping("id/{id}")
    Optional<User> getByUserId(@PathVariable Long id){
        return userService.getByUserId(id);
    }

    @PostMapping
    User createUser(@RequestBody User user){

        return userService.createUser(user);
    }


}


