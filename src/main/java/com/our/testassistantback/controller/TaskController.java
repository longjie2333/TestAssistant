package com.our.testassistantback.controller;

import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.pojo.Task;
import com.our.testassistantback.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService testService;

    @GetMapping("/all/:{pid}")
    public Result selectAll(@PathVariable Integer pid){
        List<Task> task = testService.selectAll(pid);

        return Result.success(task);
    }
    @GetMapping("/detail/:{id}")
    public Result detailById(@PathVariable Integer id){
        Task task = testService.detailById(id);
        return Result.success(task);
    }
    @PostMapping("/new")
    public Result newTask(@RequestBody Task task){
        int pid = task.getPid();
        String name = task.getName();
        String description = task.getDescription();

       int id = testService.newTask(name,description,pid);

        return Result.success(id);
    }
    @PostMapping("/modify/:{id}")
    public Result modify(@PathVariable Integer id,@RequestBody Task task){
        testService.modify(id,task);
        return Result.success();
    }
    @PostMapping("/delete/:{id}")
    public Result deleteById(@PathVariable Integer id){
        testService.deleteById(id);
        return Result.success();
    }

}
