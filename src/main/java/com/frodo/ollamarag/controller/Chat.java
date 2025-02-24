package com.frodo.ollamarag.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Chat {

    private final ChatClient chatClient;

    public Chat(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("")
    public String hello() {
        return chatClient.prompt().user("quote of the day")
                .call()
                .content();
    }

}