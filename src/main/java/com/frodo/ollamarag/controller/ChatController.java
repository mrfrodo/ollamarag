package com.frodo.ollamarag.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("")
    public String hello() {
        Long timestamp = System.currentTimeMillis();

        String quoteOfTheDay = chatClient.prompt().user("Come up with a fake fact about computer programming. Just answer with the fact. Nothing else")
                .call()
                .content();
        Long timeTaken = System.currentTimeMillis();
        System.out.println(String.format("  __Request took %s ms.\n", timeTaken - timestamp));
        System.out.println(quoteOfTheDay);
        return quoteOfTheDay;
    }



}