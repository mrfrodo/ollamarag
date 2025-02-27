package com.frodo.ollamarag.model;

import com.pgvector.PGvector;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("document") // Maps to the "document" table
public record Document(
        @Id Long id,
        String title,
        String content,
        PGvector embedding // Maps to PostgreSQL's VECTOR(1024)
) {}
