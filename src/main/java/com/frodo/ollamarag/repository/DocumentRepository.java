package com.frodo.ollamarag.repository;

import com.frodo.ollamarag.model.Document;
import com.pgvector.PGvector;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Long>, CustomDocumentRepository {
    // Spring automatically wires CustomDocumentRepositoryImpl into this interface
}