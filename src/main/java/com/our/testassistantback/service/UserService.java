package com.our.testassistantback.service;

import com.our.testassistantback.pojo.User;

public interface UserService {
    User login(User user);

    void insert(User user);

    void delete(Integer id);
}
