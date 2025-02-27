package com.frodo.ollamarag.repository;

import com.frodo.ollamarag.model.Document;
import com.pgvector.PGvector;
import java.util.List;

public interface CustomDocumentRepository {
    List<Document> findSimilarDocuments(PGvector queryVector, int limit);
    List<Document> findSimilarDocumentsStrict(PGvector queryVector, int limit);
}