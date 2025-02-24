### Ollama, Rag, LangChain4j

### Postgresql database with embeddings

LangChain4j is a Java library designed to work with Large Language Models (LLMs) like OpenAI's GPT, Claude, and others.  

### Workflow
- Ollama for Embedding Generation
- Embeddings are stored as float(8) in posthresql
- Use a Java record Document to hold the embeddings
```
@Table("document")
public record Document(@Id Long id,
                       String title,
                       String content,
                       Double[] embedding) 
{}
```
- Endpoint to create embeddings from text/pdf
- Endpoint to ask questions to the AI ONLY using the emdbeddings