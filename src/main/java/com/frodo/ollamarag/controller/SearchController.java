package com.frodo.ollamarag.controller;

import com.frodo.ollamarag.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * Uses RAG and embeddings to augment the users query
 */

@RestController
public class SearchController {
    private final DocumentService documentService;

    @Autowired
    public SearchController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/q1")
    public String q1(@RequestParam(value = "query") String query) throws IOException {
        String answer = documentService.answerUserQueryWithEmbeddings(query);
        return answer;
    }

}
