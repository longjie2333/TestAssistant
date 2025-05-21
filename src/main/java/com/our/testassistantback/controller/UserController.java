package com.our.testassistantback.controller;

import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.pojo.User;
import com.our.testassistantback.service.UserService;
import com.our.testassistantback.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @GetMapping()
    public Result getUser(){
        String jwt = request.getHeader("Authorization");
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);
        Integer id = (Integer) claims.get("id");
        String email = (String) claims.get("email");
        String group = (String) claims.get("group");
        User u = new User();
        u.setId(id);
        u.setEmail(email);
        u.setGroup(group);
        log.info("查找用户:{}",u);
        return Result.success(u);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User u =  userService.login(user);
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

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        userService.insert(user);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result delete(){
        String jwt = request.getHeader("token");
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);
        Integer id = (Integer) claims.get("id");
        userService.delete(id);
        log.info("删除用户为id：{}",id);
        return Result.success();
    }
}
