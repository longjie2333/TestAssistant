package com.our.testassistantback.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class TestCaseDataParams {
    private String query;
    private Map<String, String> path;
}
