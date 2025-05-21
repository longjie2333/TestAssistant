package com.our.testassistantback.service.impl;

import com.our.testassistantback.Mapper.UserMapper;
import com.our.testassistantback.pojo.User;
import com.our.testassistantback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.getEmailAndgetPassword(user);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }
}
