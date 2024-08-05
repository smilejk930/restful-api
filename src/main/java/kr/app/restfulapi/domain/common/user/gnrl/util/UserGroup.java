package kr.app.restfulapi.domain.common.user.gnrl.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 역할 그룹을 정의하는 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 시스템 내의 다양한 역할 그룹을 정의하며, 각 그룹은 여러 개의 {@link UserType}을 포함할 수 있습니다.
 * 역할 그룹을 사용하면 특정 권한 그룹을 보다 쉽게 관리하고 확인할 수 있습니다.
 * </p>
 * <p>
 * 예를 들어, {@link #ADMIN_GROUP}은 관리자와 유지보수사업자를 포함하고,
 * {@link #USER_GROUP}은 일반 사용자 역할을 포함합니다.
 * </p>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGroup {
  /**
   * 관리자 그룹.
   * <p>
   * 이 그룹은 관리자와 유지보수사업자 역할을 포함합니다.
   * </p>
   */
  public static final UserType[] ADMIN_GROUP = {UserType.USR000, UserType.USR001};

  /**
   * 사용자 그룹.
   * <p>
   * 이 그룹은 일반 사용자 역할을 포함합니다.
   * </p>
   */
  public static final UserType[] USER_GROUP = {UserType.USR003, UserType.USR004, UserType.USR005, UserType.USR006};
}
