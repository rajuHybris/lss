package com.hb.controller;

import com.hb.dto.ChatRequest;
import com.hb.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Autowired
    private RetryTemplate retryTemplate;

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        try {
            return retryTemplate.execute(context -> {
                ChatRequest request = new ChatRequest(model, prompt);
                ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
                if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
                    throw new RuntimeException("No response");
                }
                return response.getChoices().get(0).getMessage().getContent();
            });
        } catch (Exception e) {
            return "Default response";
        }
    }
}
