package dev.be.board.dto;

import dev.be.board.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link dev.be.board.domain.Article}
 */
public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
  public static ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
    return new ArticleDto(id, userAccountDto, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
  }
  
  public static ArticleDto from(Article entity) {
    return new ArticleDto(
            entity.getId(),
            UserAccountDto.from(entity.getUserAccount()),
            entity.getTitle(),
            entity.getContent(),
            entity.getHashtag(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getUpdatedAt(),
            entity.getUpdatedBy()
    );
  }
  
  public Article toEntity() {
    return Article.of(
            userAccountDto.toEntity(),
            title,
            content,
            hashtag
    );
  }
  
}


