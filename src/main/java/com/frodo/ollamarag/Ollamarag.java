package com.frodo.ollamarag;

import com.frodo.ollamarag.service.DocumentService;
import com.frodo.ollamarag.service.EmbeddingService;
import com.pgvector.PGvector;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Ollamarag {

	public static void main(String[] args) {
		SpringApplication.run(Ollamarag.class, args);
	}


	/*
	@Bean
	ApplicationRunner applicationRunner(DocumentService documentService, @Value("classpath:spring.pdf") Resource pdfResource) {
		return args -> {
			PdfDocumentReaderConfig config = PdfDocumentReaderConfig.builder()
					.withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder().withNumberOfBottomTextLinesToDelete(3)
							.withNumberOfTopPagesToSkipBeforeDelete(1)
							.build())
					.withPagesPerDocument(1)
					.build();
			PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(pdfResource, config);
			TokenTextSplitter textSplitter = new TokenTextSplitter();
			List<Document> aiDocuments = textSplitter.apply(pdfReader.get());
			for (Document document : aiDocuments) {
				String content = document.getFormattedContent();
				String title = content.substring(0, 10);
				documentService.saveDocument(title, content);
			}
			System.out.println("__ pdf saved to embeddings table");
		};
	}*/
}


