package dev.be.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
       @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ArticleComment {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  /** 게시글(id) */
  @Setter
  @ManyToOne(optional = false)
  private Article article;
  /** 본문 */
  @Column(nullable = false, length = 500)
  private String content;
  
  /** 생성일시 */
  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime createdAt;
  /** 생성자 */
  @CreatedBy
  @Column(nullable = false, length = 100)
  private String createdBy;
  /** 수정일시 */
  @LastModifiedDate
  private LocalDateTime updatedAt;
  /** 수정자 */
  @LastModifiedBy
  @Column(nullable = false, length = 100)
  private String updatedBy;
  
  protected ArticleComment () {}
  
  public ArticleComment(Article article, String content) {
    this.article = article;
    this.content = content;
  }
  
  public static ArticleComment of(Article article, String content) {
    return new ArticleComment(article, content);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ArticleComment that = (ArticleComment) o;
    return Objects.equals(id, that.id);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
