package kr.app.restfulapi.global.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseUpdtEntity extends BaseAuditingRegistEntity {

  @Comment("수정자아이디")
  private String updusrId;

  @Comment("수정일시")
  private LocalDateTime updtDt;

}
