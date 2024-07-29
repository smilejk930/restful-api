package kr.app.restfulapi.global.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
public abstract class BaseAuditingRegistEntity extends BaseServerEntity {

  @Comment("등록자아이디")
  @CreatedBy
  @Column(updatable = false, nullable = false)
  private String registerId;

  @Comment("등록일시")
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime registDt;

}
