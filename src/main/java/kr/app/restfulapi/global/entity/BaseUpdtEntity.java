package kr.app.restfulapi.global.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
public abstract class BaseUpdtEntity extends BaseAuditingRegistEntity {

  @Comment("수정자아이디")
  private String updusrId;

  @Comment("수정일시")
  private LocalDateTime updtDt;

}
