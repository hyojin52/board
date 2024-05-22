package dev.be.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingFields {
  /** 생성일시 */
  @CreatedDate
  @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
  @Column(nullable = false)
  private LocalDateTime createdAt;
  
  /** 생성자 */
  @CreatedBy
  @Column(nullable = false, updatable = false, length = 100)
  private String createdBy;
  
  /** 수정일시 */
  @LastModifiedDate
  @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedAt;
  
  /** 수정자 */
  @LastModifiedBy
  @Column(nullable = false, length = 100)
  private String updatedBy;
}
