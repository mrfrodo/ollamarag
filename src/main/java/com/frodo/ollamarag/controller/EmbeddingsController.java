package com.frodo.ollamarag.controller;

import com.frodo.ollamarag.model.Document;
import com.frodo.ollamarag.service.DocumentService;
import com.frodo.ollamarag.service.OllamaEmbeddingService;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Create embeddings from text/content and store it to vector database
 */

@RestController
public class EmbeddingsController {

    private final DocumentService documentService;

    @Autowired
    public EmbeddingsController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/store")
    public Map simpleStore(@RequestParam(value = "content", defaultValue = "hello world") String content) throws IOException {
        Document document = documentService.saveDocument(content.substring(0, 10), content);
        Map<String, Document> map = Map
                .of(String.format("document '%s' stored successfully as embeddings", content), document);
        return map;
    }

    @PostMapping("/store")
    public Map storeDocument(@RequestBody String content) throws IOException {
        Document document = documentService.saveDocument(content.substring(0, 10), content);
        Map<String, Document> map = Map
                .of(String.format("document '%s' stored successfully as embeddings", content), document);
        return map;
    }


    // Helper method to convert MultipartFile to a File
    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }
}

