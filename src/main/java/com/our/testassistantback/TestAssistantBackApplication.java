package com.our.testassistantback;

import com.our.testassistantback.service.mcp.ApiTestService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestAssistantBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAssistantBackApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider ApiTestTool(ApiTestService apiTestService) {
        return MethodToolCallbackProvider.builder().toolObjects(apiTestService).build();
    }
}
