package com.our.testassistantback.service;

import com.our.testassistantback.pojo.Task;

import java.util.List;

public interface TaskService {
    List<Task> selectAll(Integer pid);

    Task detailById(Integer id);

    int newTask(String name, String description,Integer pid);

    void modify(Integer id, Task task);

    void deleteById(Integer id);
}
