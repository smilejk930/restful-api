package kr.app.restfulapi.uga.common.entity;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends BaseTimeEntity {

  @Setter
  @Comment("등록자아이디")
  @CreatedBy
  @Column(updatable = false, nullable = false)
  private String registerId;

  @Setter
  @Comment("수정자아이디")
  // @LastModifiedBy
  @Column(insertable = false)
  private String updusrId;

  @Setter
  @Comment("등록서버명")
  @Column(length = 100)
  private String registServerNm;

  @Setter
  @Comment("최종서버명")
  @Column(length = 100)
  private String lastServerNm;
}