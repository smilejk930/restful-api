package kr.app.restfulapi.global.entity;

import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
public abstract class BaseServerEntity {

  @Setter
  @Comment("등록서버명")
  @Column(length = 100)
  private String registServerNm;

  @Setter
  @Comment("최종서버명")
  @Column(length = 100)
  private String lastServerNm;

}
