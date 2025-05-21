package com.our.testassistantback.service.mcp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.our.testassistantback.pojo.TestCase;
import com.our.testassistantback.pojo.TestCaseData;
import com.our.testassistantback.pojo.TestCaseDataParams;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class ApiTestService {
    @Tool(name = "运行测试用例", description = "运行用户提供的 JSON 格式的测试用例工具")
    public String apiTest(
            @ToolParam(description = "API基础地址") String baseUrl,
            @ToolParam(description = "JSON格式测试用例") JsonNode testCaseParams) {
        ObjectMapper om = new ObjectMapper();

        try {
            // 准备请求
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();

            // 解析测试用例
            TestCase testCase = om.treeToValue(testCaseParams, TestCase.class);
            TestCaseData testCaseData = testCase.getData();

            if (testCaseData.getHeaders() != null) {
                for (Map.Entry<String, String> entry : testCaseData.getHeaders().entrySet()) {
                    requestBuilder.header(entry.getKey(), entry.getValue());
                }
            }

            URI apiAddr = parseUriFromTC(baseUrl, testCaseData);

            requestBuilder.uri(apiAddr);

            // 创建HTTP客户端，执行请求
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = requestBuilder.build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, List<String>> headers = response.headers().map();
            String headersJson = om.writeValueAsString(headers);

            return String.format(
                    "Url: %s, Status: %s, Response: %s, Headers: %s",
                    apiAddr, response.statusCode(), response.body(), headersJson);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * 从测试用例数据中解析 API 地址
     * @param baseUrl 基础地址
     * @param testCaseData 测试用例数据
     * @return {@link URI} API 地址
     */
    private static URI parseUriFromTC(String baseUrl, TestCaseData testCaseData) {
        TestCaseDataParams testCaseDataParams = testCaseData.getParams();

        String urlStr = baseUrl + testCaseData.getUrl();

        if (testCaseDataParams != null) {
            urlStr = urlStr + testCaseDataParams.getQuery();

            if (testCaseDataParams.getPath() != null) {
                for (Map.Entry<String, String> entry : testCaseDataParams.getPath().entrySet()) {
                    urlStr = urlStr.replace(":" + entry.getKey(), entry.getValue());
                }
            }
        }
        return URI.create(urlStr);
    }
}
