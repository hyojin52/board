package dev.be.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
public class ArticleComment extends AuditingFields {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Setter
  @JoinColumn(name = "userId")
  @ManyToOne(optional = false)
  private UserAccount userAccount; // 유저 정보 (ID)
  
  /** 게시글(id) */
  @Setter
  @ManyToOne(optional = false)
  private Article article;
  /** 본문 */
  @Column(nullable = false, length = 500)
  private String content;
  

  
  protected ArticleComment () {}
  
  public ArticleComment(UserAccount userAccount, Article article, String content) {
    this.userAccount = userAccount;
    this.article = article;
    this.content = content;
  }
  
  public static ArticleComment of(UserAccount userAccount, Article article, String content) {
    return new ArticleComment(userAccount, article, content);
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
