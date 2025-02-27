package com.frodo.ollamarag.service;

import com.frodo.ollamarag.repository.DocumentRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    @Autowired
    public EmbeddingService(EmbeddingModel embeddingModel, DocumentRepository documentsRepository) {
        this.embeddingModel = embeddingModel;
    }

    public float[] getEmbeddingsFromStr(String str) {
        float[] embeddings = embeddingModel.embed(str);
        return embeddings;
    }
}
