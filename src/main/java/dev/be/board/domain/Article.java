package dev.be.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Article {
  
  private Long id;
  
  /* 제목 */
  private String title;
  /* 본문 */
  private String content;
  /* 해시태그 */
  private String hashtag;
  
  /* 생성일시 */
  private LocalDateTime createdAt;
  /* 생성자 */
  private String createdBy;
  /* 수정일시 */
  private LocalDateTime updatedAt;
  /* 수정자 */
  private String updatedBy;
}
