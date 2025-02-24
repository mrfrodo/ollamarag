package com.frodo.ollamarag.service;

import com.frodo.ollamarag.repository.DocumentRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Service that will create embeddings from given input.
 * Supports text input and pdf input
 */

@Service
public class OllamaEmbeddingService {

    private final EmbeddingModel embeddingModel;

    @Autowired
    public OllamaEmbeddingService(EmbeddingModel embeddingModel, DocumentRepository documentsRepository) {
        this.embeddingModel = embeddingModel;
    }

    public float[] getEmbeddingsFromText(String content) {
        float[] embeddings = embeddingModel.embed(content);
        return embeddings;

    }
    public List<Float> generateEmbeddingsFromPdf(File pdfFile) throws IOException {

        return null;
    }

    // Helper method to extract text from a PDF file using Apache PDFBox
    private String extractTextFromPdf(File pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println("  __" + text);
            return text;
        }
    }
}
