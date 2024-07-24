package kr.app.restfulapi.global.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditingUpdtEntity extends BaseAuditingRegistEntity {

  @Comment("수정자아이디")
  @LastModifiedBy
  @Column(insertable = false)
  private String updusrId;

  @Comment("수정일시")
  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime updtDt;

}
