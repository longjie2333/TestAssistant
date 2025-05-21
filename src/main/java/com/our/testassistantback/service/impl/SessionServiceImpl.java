package com.our.testassistantback.service.impl;

import com.our.testassistantback.Mapper.SessionMapper;
import com.our.testassistantback.pojo.Session;
import com.our.testassistantback.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionMapper sessionMapper;
    @Override
    public Session selectById(Integer id) {
        return sessionMapper.selectById(id);
    }
}
