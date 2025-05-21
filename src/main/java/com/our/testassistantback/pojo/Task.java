package com.our.testassistantback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private int id;
    private int pid;
    private String name;
    private String description;
    private int aid;
    private String benchmark;
    private String testcase;
}
