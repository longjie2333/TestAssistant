package com.our.testassistantback.service;

import com.our.testassistantback.pojo.Session;

public interface SessionService {
    Session selectById(Integer id);
}
