package com.our.testassistantback.service;

import com.our.testassistantback.pojo.Project;

import java.util.List;

public interface ProjectService {
    List<Project> selectProject();

    Project detailId(Integer id);

    void add(String name, String description,Integer uid);

    void modify(Integer id, int uid, String name, String description);

    void deleteById(Integer id);
}
