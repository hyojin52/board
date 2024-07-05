package dev.be.board.dto;

import java.time.LocalDateTime;

/**
 * DTO for {@link dev.be.board.domain.ArticleComment}
 */
public record ArticleCommentUpdateDto(
        String content)  {
  
  public static ArticleCommentUpdateDto of(String content) {
    return new ArticleCommentUpdateDto(content);
  }
}