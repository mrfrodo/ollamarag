### Ollama, Rag, PGVector


```
@Table("document") // Maps to the "document" table
public record Document(
        @Id Long id,
        String title,
        String content,
        PGvector embedding // Maps to PostgreSQL's VECTOR(1024)
) {}
```

```
docker run -d \
  --name my-postgres-pgvector \
  -e POSTGRES_DB=postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=pg \
  -p 5555:5432 \
  ankane/pgvector:v0.5.1
```
