package com.our.testassistantback.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.Map;

@Data
public class TestCaseData {
    private String url;
    private String method;
    private TestCaseDataParams params;
    private Map<String, String> headers;
    private Map<String, String> cookies;
    private JsonNode body;
}
