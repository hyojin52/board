package dev.be.board.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link dev.be.board.domain.ArticleComment}
 */
public record ArticleCommentDto(
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        String content)  {
  
  public static ArticleCommentDto of(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy, String content) {
    return new ArticleCommentDto(createdAt, createdBy, updatedAt, updatedBy, content);
  }
}