package com.our.testassistantback.controller;

import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.service.UserService;
import com.our.testassistantback.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class DeleteController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;
    @PostMapping("/user/delete")
    public Result delete(){
        String jwt = request.getHeader("token");
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);
        Integer id = (Integer) claims.get("id");
        userService.delete(id);
        log.info("删除用户为id：{}",id);
        return Result.success();
    }
}
