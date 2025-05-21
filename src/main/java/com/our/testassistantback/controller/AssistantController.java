package com.our.testassistantback.controller;

import com.our.testassistantback.pojo.Chat;
import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.utils.SomePrompt;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class AssistantController {
    private final OpenAiChatModel model;

    @Autowired
    public AssistantController(OpenAiChatModel model) {
        this.model = model;
    }

    @PostMapping("/chat")
    public Result chat(@RequestBody Chat chatBody) {
        String msg = chatBody.getMsg();

        if (msg == null || msg.isEmpty()) {
            return Result.error("对话信息为空");
        }

        SystemPromptTemplate sysPrompt = new SystemPromptTemplate(SomePrompt.getApiTestcaseRole());

        PromptTemplate usrPrompt = PromptTemplate.builder()
                .renderer(StTemplateRenderer.builder().startDelimiterToken('<').endDelimiterToken('>').build())
                .template(msg)
                .build();

        Prompt prompt = new Prompt(List.of(
                usrPrompt.createMessage(),
                sysPrompt.createMessage()
        ));

        return Result.success(this.model.call(prompt));
    }

    @PostMapping("/chat/stream")
    public Flux<ChatResponse> chatStream(@RequestBody Chat chatBody) {
        String msg = chatBody.getMsg();

        if (msg == null || msg.isEmpty()) {
            throw new RuntimeException("对话信息为空");
        }

        SystemPromptTemplate sysPrompt = new SystemPromptTemplate(SomePrompt.getApiTestcaseRole());

        PromptTemplate usrPrompt = PromptTemplate.builder()
                .renderer(StTemplateRenderer.builder().startDelimiterToken('<').endDelimiterToken('>').build())
                .template(msg)
                .build();

        Prompt prompt = new Prompt(List.of(
                usrPrompt.createMessage(),
                sysPrompt.createMessage()
        ));

        return this.model.stream(prompt);
    }
}
