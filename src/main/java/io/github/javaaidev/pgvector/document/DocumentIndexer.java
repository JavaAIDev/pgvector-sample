package io.github.javaaidev.pgvector.document;

import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.boot.CommandLineRunner;

public class DocumentIndexer implements CommandLineRunner {

  private final DocumentService documentService;

  public DocumentIndexer(DocumentService documentService) {
    this.documentService = documentService;
  }

  @Override
  public void run(String... args) throws Exception {
    var markerFile = Path.of(System.getProperty("user.home")).resolve(".pgvector_index");
    if (Files.exists(markerFile)) {
      return;
    }
    Files.createFile(markerFile);
    documentService.load();
  }
}
