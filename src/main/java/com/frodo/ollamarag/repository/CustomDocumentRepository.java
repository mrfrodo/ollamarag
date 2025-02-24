package com.frodo.ollamarag.repository;

import com.frodo.ollamarag.model.Document;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CustomDocumentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomDocumentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Document> findSimilarDocuments(float[] queryEmbedding) {
        String sql = "SELECT * FROM document ORDER BY embedding <=> ? LIMIT 10";

        return jdbcTemplate.query(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setArray(1, connection.createArrayOf("float4", toFloatObjectArray(queryEmbedding))); // Corrected conversion
            return ps;
        }, documentRowMapper);
    }

    // Correctly convert primitive float[] to Float[]
    private static Float[] toFloatObjectArray(float[] array) {
        if (array == null) return null;
        Float[] objectArray = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            objectArray[i] = array[i]; // Auto-boxing float to Float
        }
        return objectArray;
    }

    private final RowMapper<Document> documentRowMapper = (rs, rowNum) -> {
        Document doc = new Document(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                (Float[]) rs.getArray("embedding").getArray() // Ensure Float[], not Double[]
        );
        return doc;
    };
}

