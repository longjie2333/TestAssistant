package com.our.testassistantback.service;

import com.our.testassistantback.pojo.Task;

public interface TaskService {
    Task selectAll(Integer pid);

    Task detailById(Integer id);

    int newTask(String name, String description,Integer pid);

    void modify(Integer id, Task task);

    void deleteById(Integer id);
}
