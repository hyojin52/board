package dev.be.board.service;

import dev.be.board.domain.Article;
import dev.be.board.domain.ArticleComment;
import dev.be.board.dto.ArticleCommentDto;
import dev.be.board.dto.ArticleCommentUpdateDto;
import dev.be.board.dto.ArticleDto;
import dev.be.board.repository.ArticleCommentRepository;
import dev.be.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {
  @InjectMocks
  private ArticleCommentService articleCommentService;
  @Mock
  private ArticleCommentRepository articleCommentRepository;
  @Mock
  private ArticleRepository articleRepository;
  
  @DisplayName("게시글 ID 로 조회하면, 해당하는 댓글 리스트를 반환한다.")
  @Test
  void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {
    // Given
    Long articleId = 1L;
    given(articleRepository.findById(articleId))
            .willReturn(Optional.of(Article.of("title", "content", "#java")));
    
    // When
    List<ArticleCommentDto> articleCommentList = articleCommentService.searchArticleComment(articleId);
    
    // Then
    assertThat(articleCommentList).isNotNull();
    then(articleRepository).should().findById(articleId);
  }
  
  @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
  @Test
  void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
    // Given
    given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);
    
    // When
    articleCommentService.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "Fastcampus", LocalDateTime.now(), "Fastcampus", "content"));
    
    // Then
    then(articleCommentRepository).should().save(any(ArticleComment.class));
  }
  
  @DisplayName("댓글 ID를 입력하면, 댓글을 수정한다.")
  @Test
  void givenArticleCommentId_whenUpdatingArticleComment_thenUpdatesArticleComment() {
    // Given
    given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);
    
    // When
    articleCommentService.updateArticleComment(1L, ArticleCommentUpdateDto.of("content updated"));
    
    // Then
    then(articleCommentRepository).should().save(any(ArticleComment.class));
  }
  
  @DisplayName("댓글 ID를 입력하면, 댓글을 삭제한다.")
  @Test
  void givenArticleCommentId_whenDeletingArticleComment_thenDeletesArticleComment() {
    // Given
    willDoNothing().given(articleCommentRepository).delete(any(ArticleComment.class));
    
    // When
    articleCommentService.deleteArticleComment(1L);
    
    // Then
    then(articleCommentRepository).should().delete(any(ArticleComment.class));
  }
  
  
}