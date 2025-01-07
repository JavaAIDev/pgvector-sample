package io.github.javaaidev.pgvector.document;

import io.github.javaaidev.pgvector.db.ShowService;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;

public class DocumentService {

  private final VectorStore vectorStore;
  private final ShowService showService;

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentService.class);

  public DocumentService(VectorStore vectorStore, ShowService showService) {
    this.vectorStore = vectorStore;
    this.showService = showService;
  }

  public void load() {
    LOGGER.info("Load documents into vector store");
    var shows = showService.listShows(5);
    var docs = shows.stream().map(show -> Document.builder()
        .id(UUID.nameUUIDFromBytes(show.showId().getBytes()).toString())
        .text(show.description())
        .build()).toList();
    vectorStore.add(docs);
  }
}
