package dev.be.board.service;

import dev.be.board.domain.type.SearchType;
import dev.be.board.dto.ArticleDto;
import dev.be.board.dto.ArticleUpdateDto;
import dev.be.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
  private final ArticleRepository articleRepository;
  
  @Transactional(readOnly = true)
  public Page<ArticleDto> searchArticles(SearchType searchType, String keyword) {
    return Page.empty();
  }
  
  @Transactional(readOnly = true)
  public ArticleDto searchArticle(long l) {
    return null;
  }
  
  public void saveArticle(ArticleDto dto) {
  }
  
  public void updateArticle(long articleId, ArticleUpdateDto dto) {
  }
  
  public void deleteArticle(long articleId) {
  
  }
}
