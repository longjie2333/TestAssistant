package com.our.testassistantback.controller;

import com.our.testassistantback.pojo.Project;
import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.service.ProjectService;
import com.our.testassistantback.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ProjectController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/all")
    public Result getProject(){
        List<Project> projects = projectService.selectProject();
        return Result.success(projects);
    }
    @GetMapping("/project/detail/:{id}")
    public Result detailID(@PathVariable Integer id){
        Project project = projectService.detailId(id);
        return Result.success(project);
    }

    @PostMapping("/project/new")
    public Result addProject(@RequestBody Project project){
        try {
            String authorization = httpServletRequest.getHeader("Authorization");
            Claims claims = JwtUtils.parseJWT(authorization);
            int id = claims.get("id", Integer.class);
            project.setUid(id);
            int uid = project.getUid();
            String name = project.getName();
            String description = project.getDescription();
            projectService.add(name,description,uid);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return Result.success();
    }
    @PostMapping("/project/modify/:{id}")
    public Result modify(@PathVariable Integer id,@RequestBody Project p){
        int uid = p.getUid();
        String name = p.getName();
        String description = p.getDescription();
        projectService.modify(id,uid,name,description);
        return Result.success();
    }
    @PostMapping("/project/delete/:{id}")
    public Result deleteById(@PathVariable Integer id){
        projectService.deleteById(id);
        return Result.success();
    }
}
