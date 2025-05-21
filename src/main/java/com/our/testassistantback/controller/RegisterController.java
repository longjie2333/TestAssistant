package com.our.testassistantback.controller;

import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.pojo.User;
import com.our.testassistantback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;
    @PostMapping("/user/register")
    public Result register(@RequestBody User user){
        userService.insert(user);
        return Result.success();
    }
}
