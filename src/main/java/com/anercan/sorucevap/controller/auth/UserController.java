package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.entity.User;
import com.anercan.sorucevap.client.resource.JsonResponse;
import com.anercan.sorucevap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth/users")
@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping("id/{id}")
    JsonResponse<Optional<User>> getByUserId(@PathVariable Long id) {
        return createJsonResponse(userService.getById(id)); //todo UserResource don ve passwordu setleme
    }


    //todo user pasife cekme

}



