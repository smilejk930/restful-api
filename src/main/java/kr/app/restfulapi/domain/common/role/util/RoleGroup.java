package kr.app.restfulapi.domain.common.role.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 역할 그룹을 정의하는 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 시스템 내의 다양한 역할 그룹을 정의하며, 각 그룹은 여러 개의 {@link RoleName}을 포함할 수 있습니다.
 * 역할 그룹을 사용하면 특정 권한 그룹을 보다 쉽게 관리하고 확인할 수 있습니다.
 * </p>
 * <p>
 * 예를 들어, {@link #ADMIN_GROUP}은 시스템 관리자와 중간 관리자를 포함하고,
 * {@link #USER_GROUP}은 일반 사용자 역할을 포함합니다.
 * </p>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleGroup {
  /**
   * 관리자 그룹.
   * <p>
   * 이 그룹은 시스템 관리자와 중간 관리자 역할을 포함합니다.
   * </p>
   */
  public static final RoleName[] ADMIN_GROUP = {RoleName.ADMIN, RoleName.INTERMEDIATE_ADMIN};

  /**
   * 사용자 그룹.
   * <p>
   * 이 그룹은 일반 사용자 역할을 포함합니다.
   * </p>
   */
  public static final RoleName[] USER_GROUP = {RoleName.USER};

  // 다른 그룹들도 필요에 따라 정의
}
