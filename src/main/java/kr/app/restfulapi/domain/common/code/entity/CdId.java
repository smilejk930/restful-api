package kr.app.restfulapi.domain.common.code.entity;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cd 엔터티 복합키
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CdId implements Serializable {

  private static final long serialVersionUID = 1L;

  private String cdGroupNm;
  private String cdNm;
}
