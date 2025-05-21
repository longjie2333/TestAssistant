package com.our.testassistantback.service.impl;

import com.our.testassistantback.Mapper.TaskMapper;
import com.our.testassistantback.pojo.Task;
import com.our.testassistantback.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Override
    public Task selectAll(Integer pid) {
        return taskMapper.selectAll(pid);
    }

    @Override
    public Task detailById(Integer id) {
        return taskMapper.detailById(id);
    }

    @Override
    public int newTask(String name, String description,Integer pid) {
       // Task task = taskMapper.newTask(name, description, pid);
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setPid(pid);  // 确保传入 pid（外键需有效）

        return taskMapper.newTask(task);
    }

    @Override
    public void modify(Integer id, Task task) {
        taskMapper.modify(id,task);
    }

    @Override
    public void deleteById(Integer id) {
        taskMapper.deleteById(id);
    }
}
