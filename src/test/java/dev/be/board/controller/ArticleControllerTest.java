package dev.be.board.controller;

import dev.be.board.config.SecurityConfig;
import dev.be.board.dto.ArticleWithCommentsDto;
import dev.be.board.dto.UserAccountDto;
import dev.be.board.service.ArticleService;
import dev.be.board.service.PaginationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class)
@Import(SecurityConfig.class)
class ArticleControllerTest {
  
  private final MockMvc mockMvc;
  
  @MockBean
  private ArticleService articleService;
  
  @MockBean
  private PaginationService paginationService;
  
  ArticleControllerTest(
          @Autowired MockMvc mockMvc
  ) {
    this.mockMvc = mockMvc;
  }
  
  @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 페이징, 정렬 기능")
  @Test
  public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView () throws Exception {
    // Given
    given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
    given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));
    
    // When & Then
    mockMvc.perform(get("/articles"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect(view().name("articles/index"))
            .andExpect(model().attributeExists("articles"));
    
    then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
    then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
  }
  
  @DisplayName("[view][GET] 게시글 상세 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticleView_thenReturnsArticleView () throws Exception {
    // Given
    Long articleId = 1L;
    given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());
    
    // When & Then
    mockMvc.perform(get("/articles/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect(view().name("articles/detail"))
            .andExpect(model().attributeExists("article"));
    then(articleService).should().getArticle(articleId);
  }
  
  @Disabled("개발중")
  @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticlesSearchView_thenReturnsArticlesSearchView () throws Exception {
    // Given
    
    // When & Then
    mockMvc.perform(get("/articles/search"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
  }
  
  @Disabled("개발중")
  @DisplayName("[view][GET] 게시글 해시태그 검색 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticlesHashtagSearchView_thenReturnsArticlesHashtagSearchView () throws Exception {
    // Given
    
    // When & Then
    mockMvc.perform(get("/articles/search-hashtag"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
  }
  
  private ArticleWithCommentsDto createArticleWithCommentsDto() {
    return ArticleWithCommentsDto.of(
            1L,
            createUserAccountDto(),
            Set.of(),
            "title",
            "content",
            "#jave",
            LocalDateTime.now(),
            "uno",
            LocalDateTime.now(),
            "uno"
    );
  }
  
  private UserAccountDto createUserAccountDto() {
    return UserAccountDto.of(
            1L,
            "uno",
            "pw",
            "uno@email.com",
            "Uno",
            "memo",
            LocalDateTime.now(),
            "uno",
            LocalDateTime.now(),
            "uno"
    );
  }
}