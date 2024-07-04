package dev.be.board.service;

import dev.be.board.dto.ArticleCommentDto;
import dev.be.board.dto.ArticleCommentUpdateDto;
import dev.be.board.repository.ArticleCommentRepository;
import dev.be.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleCommentService {
  private final ArticleRepository articleRepository;
  
  private final ArticleCommentRepository articleCommentRepository;
  
  
  public List<ArticleCommentDto> searchArticleComments(Long articleId) {
    return List.of();
  }
  
  public void saveArticleComment(ArticleCommentDto dto) {
  
  }
  
  public void updateArticleComment(ArticleCommentDto dto) {
  
  }
  
  public void deleteArticleComment(long articleCommentId) {
  
  }
}
