package dev.be.board.dto;

import dev.be.board.domain.Article;
import dev.be.board.domain.ArticleComment;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link dev.be.board.domain.ArticleComment}
 */
public record ArticleCommentDto(
        Long id,
        Long articleId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
  public static ArticleCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
    return new ArticleCommentDto(id, articleId, userAccountDto, content, createdAt, createdBy, modifiedAt, modifiedBy);
  }
  
  public static ArticleCommentDto from(ArticleComment entity) {
    return new ArticleCommentDto(
            entity.getId(),
            entity.getArticle().getId(),
            UserAccountDto.from(entity.getUserAccount()),
            entity.getContent(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getUpdatedAt(),
            entity.getUpdatedBy()
    );
  }
  
  public ArticleComment toEntity(Article entity) {
    return ArticleComment.of(
            userAccountDto.toEntity(),
            entity,
            content
    );
  }
  
}