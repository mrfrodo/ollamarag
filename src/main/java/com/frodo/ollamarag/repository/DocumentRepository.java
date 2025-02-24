package com.frodo.ollamarag.repository;

import com.frodo.ollamarag.model.Document;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Vector;

public interface DocumentRepository extends CrudRepository<Document, Long> {

    // Custom query to find similar documents by embedding similarity
    @Query("SELECT * FROM document ORDER BY embedding <=> CAST(:queryEmbedding AS vector) LIMIT 10")
    List<Document> findSimilarDocuments(float[] queryEmbedding);
}