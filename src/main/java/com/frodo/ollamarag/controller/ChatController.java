package com.frodo.ollamarag.controller;

import com.frodo.ollamarag.model.Document;
import com.frodo.ollamarag.model.DocumentBatchRequest;
import com.frodo.ollamarag.service.DocumentService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final DocumentService documentService;

    public ChatController(ChatClient.Builder builder, DocumentService documentService) {
        this.chatClient = builder.build();
        this.documentService = documentService;
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

    @GetMapping("norag")
    public String question(@RequestParam(value = "question", defaultValue = "why is the earth spinning ?") String question) throws IOException {
        Long timestamp = System.currentTimeMillis();

        String quoteOfTheDay = chatClient.prompt().user(question)
                .call()
                .content();
        Long timeTaken = System.currentTimeMillis();
        System.out.println(String.format("  __Request took %s ms.\n", timeTaken - timestamp));
        System.out.println(quoteOfTheDay);
        return quoteOfTheDay;
    }

    @GetMapping("/rag")
    public String rag(@RequestParam(value = "question", defaultValue = "why is the earth spinning ?") String question) throws IOException {

        Long timestamp = System.currentTimeMillis();

        String answer = documentService.similaritySearch(question);
        Long timeTaken = System.currentTimeMillis();
        System.out.println(String.format("  __Request took %s ms.\n", timeTaken - timestamp));
        System.out.println(answer);
        return answer;
    }

    @GetMapping("/save")
    public Map saveText(@RequestParam(value = "content", defaultValue = "hello world") String content) throws IOException {
        Document document = documentService.saveDocument(content.substring(0, 10), content);
        Map<String, Document> map = Map
                .of(String.format("document '%s' stored successfully as embeddings", content), document);
        return map;
    }

    public Map<String, Document> savePdf(@RequestBody MultipartFile file) throws IOException {
        return null;
    }

    @PostMapping("/batch-save")
    public String batchSaveDocuments(@RequestBody DocumentBatchRequest request) {
        List<Document> savedDocs = request.documents().stream()
                .map(doc -> documentService.saveDocument(doc.title(), doc.content()))
                .collect(Collectors.toList());

        return savedDocs.size() + " documents stored successfully!";
    }

    @GetMapping("/treasure")
    public Map<String, String> treasureFacts() {
        String answer = chatClient.prompt()
                .user("Tell me a really interesting fact about famous pirate treasures. " +
                        "Please keep your answer to 1 or 2 sentences.")
                .call()
                .content();

        Map<String, String> map = Map
                .of(String.format("Treasure story"), answer);

        return map;
    }


}