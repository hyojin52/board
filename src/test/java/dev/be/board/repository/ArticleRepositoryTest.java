package dev.be.board.repository;

import dev.be.board.config.JpaConfig;
import dev.be.board.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class ArticleRepositoryTest {
  @Autowired
  private ArticleRepository articleRepository;
  
  @DisplayName("select 테스트")
  @Test
  public void givenTestData_whenSelecting_thenWorksFine() {
    // Given
    
    // When
    List<Article> articles = articleRepository.findAll();
    
    // Then
    assertThat(articles)
            .isNotNull()
            .hasSize(26);
  }
  
  @DisplayName("insert 테스트")
  @Test
  public void givenTestData_whenInserting_thenWorksFine() {
    // Given
    long prevCount = articleRepository.count();
    Article article = Article.of("new article", "new content", "#hashtag");
    
    // When
    articleRepository.save(article);
    
    // Then
    assertThat(articleRepository.count())
            .isEqualTo(prevCount + 1);
  }
  
  @DisplayName("update 테스트")
  @Test
  public void givenTestData_whenUpdating_thenWorksFine() {
    // Given
    Article article = articleRepository.findById(1L).orElseThrow();
    String updatedHashtag = "#springboot_new";
    article.setHashtag(updatedHashtag);
    
    // When
    Article savedArticle = articleRepository.save(article);
    
    // Then
    assertThat(savedArticle)
            .hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
  }
  
  @DisplayName("delete 테스트")
  @Test
  public void givenTestData_whenDeleting_thenWorksFine() {
    // Given
    long prevCount = articleRepository.count();
    Article article = articleRepository.findById(1L).orElseThrow();
    
    // When
    articleRepository.delete(article);
    
    // Then
    assertEquals(articleRepository.count(), prevCount - 1);
  }
}