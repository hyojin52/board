package dev.be.board.service;

import dev.be.board.domain.Article;
import dev.be.board.domain.type.SearchType;
import dev.be.board.dto.ArticleDto;
import dev.be.board.dto.ArticleUpdateDto;
import dev.be.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시판")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
  
  @InjectMocks
  private ArticleService articleService;
  @Mock
  private ArticleRepository articleRepository;
  
  @DisplayName("게시글을 검색하면, 게시글 리스트를 반환하다.")
  @Test
  void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
    // Given
    
    // When
    Page<ArticleDto> articles = articleService.searchArticles(SearchType.TITLE, "keyword");
    
    // Then
    assertThat(articles).isNotNull();
    
  }
  
  @DisplayName("게시글을 조회하면, 게시글을 반환하다.")
  @Test
  void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
    // Given
    
    // When
    ArticleDto article = articleService.searchArticle(1L);
    
    // Then
    assertThat(article).isNotNull();
    
  }
  
  @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다")
  @Test
  void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
    // Given
    given(articleRepository.save(any(Article.class))).willReturn(null);
    
    // When
    articleService.saveArticle(ArticleDto.of(LocalDateTime.now(), "Fastcampus", "title", "content", "hahshtag"));
    
    // Then
    then(articleRepository).should().save(any(Article.class));
  }
  
  @DisplayName("게시글의 ID 와 수정 정보를 입력하면, 게시글을 수정한다")
  @Test
  void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {
    // Given
    given(articleRepository.save(any(Article.class))).willReturn(null);
    
    // When
    articleService.updateArticle(1L, ArticleUpdateDto.of("title", "content", "hahshtag"));
    
    // Then
    then(articleRepository).should().save(any(Article.class));
  }
  
  @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다")
  @Test
  void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
    // Given
    willDoNothing().given(articleRepository).delete(any(Article.class));
    
    // When
    articleService.deleteArticle(1L);
    
    // Then
    then(articleRepository).should().delete(any(Article.class));
  }
}