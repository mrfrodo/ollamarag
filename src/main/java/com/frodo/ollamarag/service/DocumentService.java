package com.frodo.ollamarag.service;

import com.frodo.ollamarag.model.Document;
import com.frodo.ollamarag.repository.CustomDocumentRepository;
import com.frodo.ollamarag.repository.DocumentRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This service will save a given document (title and content)
 * It will ask the ollama embedding service for embeddings for the content
 *
 * This service also have findSimilarDocument service (RAG)
 *
 */

@Service
public class DocumentService {

    private final ChatClient chatClient;
    private final DocumentRepository documentRepository;
    private final CustomDocumentRepository customDocumentRepository;
    private final OllamaEmbeddingService ollamaEmbeddingService;

    public DocumentService(ChatClient.Builder builder,
                           DocumentRepository documentRepository,
                           CustomDocumentRepository customDocumentRepository,
                           OllamaEmbeddingService ollamaEmbeddingService) {
        this.chatClient = builder.build();
        this.documentRepository = documentRepository;
        this.customDocumentRepository = customDocumentRepository;
        this.ollamaEmbeddingService = ollamaEmbeddingService;
    }

    public Document saveDocument(String title, String content) throws IOException {
        float[] embedding = ollamaEmbeddingService.getEmbeddingsFromText(content);
        Document document = new Document(null, title, content, toFloat(embedding));
        Document doc = documentRepository.save(document);
        return doc;
    }

    public String answerUserQueryWithEmbeddings(String userQuery) throws IOException {
        // Step 1: Generate query embedding
        float[] embeddings  = ollamaEmbeddingService.getEmbeddingsFromText(userQuery);

        // Step 2: Retrieve the most relevant documents
        List<Document> relevantDocs = documentRepository.findSimilarDocuments(embeddings);

        // Step 3: Extract text content from the documents
        String combinedText = relevantDocs.stream()
                .map(Document::content)
                .collect(Collectors.joining("\n\n"));


        String systemPrompt = """
            You are an AI assistant. Use the provided context to answer the user's question accurately and concisely.
            If you do not know the answer simply answer I DO NOT KNOW.
            """;

        String userPrompt = "Context:\n" + combinedText + "\n\n" +
                "User Question: " + userQuery + "\n\n" +
                "Provide a helpful and well-structured response.";

        String answer = chatClient.prompt().user(userPrompt).system(systemPrompt)
                .call()
                .content();

        return answer;
    }

    public String answerUserQueryWithoutEmbeddings(String userQuery) throws IOException {

        String systemPrompt = """
            You are an AI assistant. Use the provided context to answer the user's question accurately and concisely.
            If you do not know the answer simply answer I DO NOT KNOW.
            """;

        String userPrompt = "Context:\n" + null + "\n\n" +
                "User Question: " + userQuery + "\n\n" +
                "Provide a helpful and well-structured response.";

        String answer = chatClient.prompt().user(userPrompt).system(systemPrompt)
                .call()
                .content();

        return answer;
    }

    private static Float[] toFloat(float[] array) {
        if (array == null) {
            return null; // Handle null case if necessary
        }

        // Convert each float in the primitive array to a Float in the object array
        Float[] objectArray = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            objectArray[i] = array[i]; // Autoboxing: primitive float to Float
        }

        return objectArray;
    }
}
