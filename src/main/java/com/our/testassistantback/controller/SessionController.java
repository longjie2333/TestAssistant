package com.our.testassistantback.controller;

import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.pojo.Session;
import com.our.testassistantback.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @Autowired
    private SessionService sessionService;

    public Result selectById(@PathVariable Integer id){
        Session session = sessionService.selectById(id);
        String history = session.getHistory();
        return Result.success(history);
    }
}
