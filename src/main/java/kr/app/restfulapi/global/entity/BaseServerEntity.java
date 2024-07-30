package kr.app.restfulapi.global.entity;

import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import kr.app.restfulapi.global.util.ServerEntityListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(ServerEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
public abstract class BaseServerEntity {

  @Comment("등록서버명")
  @Column(length = 100)
  private String regSrvrNm;

  @Comment("최종서버명")
  @Column(length = 100)
  private String lastSrvrNm;
}
