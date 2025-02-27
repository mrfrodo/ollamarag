### Ollama, Rag, PGVector


```
@Table("document")
public record Document(@Id Long id,
                       String title,
                       String content,
                       Double[] embedding) 
{}
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
