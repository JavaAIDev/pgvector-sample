package io.github.javaaidev.pgvector;

import io.github.javaaidev.pgvector.db.ShowService;
import io.github.javaaidev.pgvector.document.DocumentIndexer;
import io.github.javaaidev.pgvector.document.DocumentService;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfiguration {

  @Bean
  public ShowService showService(JdbcTemplate jdbcTemplate) {
    return new ShowService(jdbcTemplate);
  }

  @Bean
  public DocumentService documentService(VectorStore vectorStore, ShowService showService) {
    return new DocumentService(vectorStore, showService);
  }

  @Bean
  public DocumentIndexer documentIndexer(DocumentService documentService) {
    return new DocumentIndexer(documentService);
  }
}
