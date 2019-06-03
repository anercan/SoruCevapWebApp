package com.anercan.sorucevap.controller;

import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    Optional<User> getByUserId(@PathVariable Long id){
        return userService.getByUserId(id);
    }

    @PostMapping
    User createUser(@RequestBody User user){

        return userService.createUser(user);
    }


}



