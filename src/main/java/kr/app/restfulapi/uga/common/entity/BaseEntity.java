package kr.app.restfulapi.uga.common.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @Comment("등록자아이디")
  // @CreatedBy
  @Column(nullable = false)
  private String registerId = "1";

  @Comment("수정자아이디")
  // @LastModifiedBy
  private String updusrId;

  @Comment("등록일시")
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime registDt;

  @Comment("수정일시")
  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime updtDt;

  @Comment("등록서버명")
  @Column(length = 100)
  private String registServerNm;

  @Comment("최종서버명")
  @Column(length = 100)
  private String lastServerNm;
}
