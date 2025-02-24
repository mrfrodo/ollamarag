package com.frodo.ollamarag.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("document")
public record Document(@Id Long id,
                       String title,
                       String content,
                       Float[] embedding) {
}
