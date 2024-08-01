package kr.app.restfulapi.domain.common.menu.entity;

import java.io.Serializable;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MenuAuthrt 엔터티 복합키
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MenuAuthrtId implements Serializable {

  private static final long serialVersionUID = 1L;

  private String menuTsid;
  private UserType userTypeCd;
}
