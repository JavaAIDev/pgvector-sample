package io.github.javaaidev.pgvector.db;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class ShowService {

  private final JdbcTemplate jdbcTemplate;

  public ShowService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Show> listShows(int limit) {
    return jdbcTemplate.query("SELECT * FROM netflix_shows LIMIT " + Math.max(limit, 1),
        (rs, rowNum) -> new Show(rs.getString("show_id"), rs.getString("description")));
  }
}
