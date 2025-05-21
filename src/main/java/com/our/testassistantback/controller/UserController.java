package com.our.testassistantback.controller;

import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.pojo.User;
import com.our.testassistantback.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
//@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/user")
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


}
