package dev.be.board.controller;

import dev.be.board.domain.Article;
import dev.be.board.dto.ArticleDto;
import dev.be.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainControllerTest.class)
class MainControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ArticleRepository articleRepository;
  
  @Test
  void givenNothing_whenRequestingRootPage_thenRedirectsToArticlesPage() throws Exception {
    // Given
    
    // When & Then
    mockMvc.perform(get("/"))
            .andExpect(status().is3xxRedirection());
  }
  
}