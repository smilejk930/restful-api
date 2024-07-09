package kr.app.restfulapi.global.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

  @Comment("등록일시")
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime registDt;

  @Setter
  @Comment("수정일시")
  // @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime updtDt;
}
