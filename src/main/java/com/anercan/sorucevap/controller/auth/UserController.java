package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.dto.UserDto;
import com.anercan.sorucevap.resource.JsonResponse;
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

    @GetMapping("id/{id}")
    JsonResponse<Optional<User>> getByUserId(@PathVariable Long id) {
        return userService.getByUserId(id);
    }


    //user pasife cekme

}



