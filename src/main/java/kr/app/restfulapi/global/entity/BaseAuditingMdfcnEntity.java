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
public abstract class BaseAuditingMdfcnEntity extends BaseAuditingRegEntity {

  @Comment("수정자식별번호")
  @LastModifiedBy
  @Column(insertable = false, length = 13, columnDefinition = "CHAR(13)")
  private String mdfrTsid;

  @Comment("수정일시")
  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime mdfcnDt;

}
