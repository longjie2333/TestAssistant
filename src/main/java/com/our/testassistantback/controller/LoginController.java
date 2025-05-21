package com.our.testassistantback.controller;

import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.pojo.User;
import com.our.testassistantback.service.UserService;
import com.our.testassistantback.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
//@RequestMapping("/login")
@RestController
public class LoginController {
    @Autowired
    private UserService userservice;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        User u =  userservice.login(user);
        if(u!=null){
            log.info("登录用户：{}",u);
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("email",u.getEmail());
            claims.put("password",u.getPassword());
            claims.put("group",u.getGroup());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("账号或密码错误，或者请看有没有注册过");
    }
}
