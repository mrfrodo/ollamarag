package com.frodo.ollamarag.repository;

import com.frodo.ollamarag.model.Document;
import com.pgvector.PGvector;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomDocumentRepositoryImpl implements CustomDocumentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomDocumentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Document> findSimilarDocuments(PGvector queryVector, int limit) {

        String sql = """
            SELECT * FROM document ORDER BY embedding <=> CAST(? AS vector) LIMIT ?
        """;

        return jdbcTemplate.query(sql, new Object[]{queryVector.toString(), limit}, new DocumentRowMapper());
    }

    @Override
    public List<Document> findSimilarDocumentsStrict(PGvector queryVector, int limit) {
        double similarityThreshold = 0.5; // Define your threshold (lower values mean higher similarity)

        String sql = """
            SELECT * FROM document 
            WHERE embedding <=> CAST(? AS vector) < ?
            ORDER BY embedding <=> CAST(? AS vector)
            LIMIT ?
        """;

        return jdbcTemplate.query(sql, new Object[]{
                queryVector.toString(), similarityThreshold, queryVector.toString(), limit
        }, new DocumentRowMapper());
    }

    private static class DocumentRowMapper implements RowMapper<Document> {
        @Override
        public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Document(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    new PGvector(rs.getString("embedding")) // Convert from PG string to PGvector
            );
        }
    }
}
