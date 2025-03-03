package com.frodo.ollamarag.service;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import com.frodo.ollamarag.model.Document;
import com.frodo.ollamarag.repository.DocumentRepository;
import com.pgvector.PGvector;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This service will save a given document (title and content)
 * It will ask the ollama embedding service for embeddings for the content
 *
 * This service also have findSimilarDocument service (RAG) using AI to generate answer
 *
 */

@Service
public class DocumentService {

    private final ChatClient chatClient;
    private final DocumentRepository documentRepository;
    private final EmbeddingService embeddingService;

    public DocumentService(ChatClient.Builder builder, DocumentRepository documentRepository, EmbeddingService embeddingService) {
        this.chatClient = builder.build();
        this.documentRepository = documentRepository;
        this.embeddingService = embeddingService;
    }

    public Document saveDocument(String title, String content) {
        // get embeddings from embeddingservice
        float[] embeddingArray = embeddingService.getEmbeddingsFromStr(content);
        // Convert float[] to PGvector
        PGvector embedding = new PGvector(embeddingArray);
        Document document = new Document(null, title, content, embedding);
        documentRepository.save(document);
        return document;
    }

    public String similaritySearch(String question) throws IOException {
        // 1. Get the embedding for the user query
        float[] userQueryEmbedding = embeddingService.getEmbeddingsFromStr(question);
        PGvector queryVector = new PGvector(userQueryEmbedding);

        // 2. Retrieve the most relevant documents based on similarity to the query
        List<Document> documents = documentRepository.findSimilarDocumentsStrict(queryVector, 5); // e.g., top 3 similar docs

        // 3. Convert documents into a structured format for the AI
        StringBuilder documentContent = new StringBuilder();
        for (Document doc : documents) {
            documentContent.append("Title: ").append(doc.title()).append("\n");
            documentContent.append("Content: ").append(doc.content()).append("\n\n");
        }

        String systemPrompt = """
                        
            You're an AI assisting with questions about computers and spring boot.
                    
            Use ONLY the information from the DOCUMENTS section to provide accurate answers but act as if you knew this information innately.
            If unsure, simply answer I DO NOT KNOW.
                    
            DOCUMENTS:
            {documents}
                        
            """;

        // 5. Properly inject formatted documents into the system prompt
        Message systemMessage = new SystemPromptTemplate(systemPrompt)
                .createMessage(Map.of("documents", documentContent.toString()));


        // 6. Get AI-generated response
        String answer = chatClient.prompt()
                .user(question)
                .system(systemMessage.getText())
                .call()
                .content();

        return answer;
    }

}
