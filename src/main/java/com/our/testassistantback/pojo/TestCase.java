package com.our.testassistantback.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class TestCase {
    private String tcid;
    private String type;
    private String goal;
    private TestCaseData data;
    private JsonNode expect;
    private String[] step;
}