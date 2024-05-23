package dev.be.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("View 컨트롤러 - 인증")
@WebMvcTest
public class AuthControllerTest {
  
  private final MockMvc mockMvc;
  
  AuthControllerTest(
          @Autowired MockMvc mockMvc
  ) {
    this.mockMvc = mockMvc;
  }
  
  @DisplayName("[view][GET] 로그인 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingLoginView_thenReturnsLoginView () throws Exception {
    // Given
    
    // When & Then
    mockMvc.perform(get("/login"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
  }
}
