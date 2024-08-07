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
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  /** 유저 정보 (ID) */
  @Setter
  @JoinColumn(name = "user_account_id")
  @ManyToOne(optional = false)
  private UserAccount userAccount;
  
  /** 제목 */
  @Setter
  @Column(nullable = false)
  private String title;
  
  /** 본문 */
  @Setter
  @Column(nullable = false, length = 10000)
  private String content;
  
  /** 해시태그 */
  @Setter
  private String hashtag;
  
  @ToString.Exclude
  @OrderBy("createdAt DESC")
  @OneToMany(mappedBy = "article", cascade = {CascadeType.ALL})
  private final Set<ArticleComment> articleComments = new LinkedHashSet<>();
  
  protected Article () {}
  
  public Article(UserAccount userAccount, String title, String content, String hashtag) {
    this.userAccount = userAccount;
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }
  
  public static Article of (UserAccount userAccount, String title, String content, String hashtag) {
    return new Article(userAccount, title, content, hashtag);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return Objects.equals(id, article.id);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
