package com.our.testassistantback.service.impl;

import com.our.testassistantback.Mapper.ProjectMapper;
import com.our.testassistantback.pojo.Project;
import com.our.testassistantback.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Project> selectProject() {

        return projectMapper.selectAll();
    }

    @Override
    public Project detailId(Integer id) {
        return projectMapper.detailId(id);
    }



    @Override
    @Transactional
    public void add(String name, String description, Integer uid) {
        try {
            projectMapper.add(name,description,uid);
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }

    @Override
    public void modify(Integer id, int uid, String name, String description) {
        projectMapper.modify(id,uid,name,description);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        try {
            projectMapper.deleteById(id);
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }
}
