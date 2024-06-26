package dev.be.board.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Data REST API 통합 테스트는 제외 처리")
@DisplayName("api 통합 테스트")
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {
  
  @Autowired
  private MockMvc mvc;
  
  @DisplayName("[api] 게시글 리스트 조회")
  @Test
  void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
    // Given
    
    // When & Then
    mvc.perform(get("/api/articles"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }
  
  @DisplayName("[api] 게시글 단건 조회")
  @Test
  void givenNothing_whenRequestingArticle_thenReturnsArticleJsonResponse() throws Exception {
    // Given
    
    // When & Then
    mvc.perform(get("/api/articles/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }
  
  @DisplayName("[api] 댓글 리스트 조회")
  @Test
  void givenNothing_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
    // Given
    
    // When & Then
    mvc.perform(get("/api/articleComments"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }
  
  @DisplayName("[api] 댓글 단건 조회")
  @Test
  void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentJsonResponse() throws Exception {
    // Given
    
    // When & Then
    mvc.perform(get("/api/articleComments/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }
  
}
