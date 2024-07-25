package kr.app.restfulapi.global.entity;

import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import kr.app.restfulapi.global.util.ServerEntityListener;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(ServerEntityListener.class)
public abstract class BaseServerEntity {

  @Comment("등록서버명")
  @Column(length = 100)
  private String registServerNm;

  @Comment("최종서버명")
  @Column(length = 100)
  private String lastServerNm;
}
