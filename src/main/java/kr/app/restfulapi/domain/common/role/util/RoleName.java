package kr.app.restfulapi.domain.common.role.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 사용자 역할을 정의하는 열거형 클래스입니다.
 * <p>
 * 이 열거형은 시스템 내에서 사용되는 다양한 사용자 역할을 정의합니다.
 * 각 역할은 시스템의 특정 권한과 책임을 나타냅니다.
 * </p>
 * <ul>
 * <li>{@link #ADMIN} - 시스템 관리자 역할</li>
 * <li>{@link #USER} - 일반 사용자 역할</li>
 * <li>{@link #INTERMEDIATE_ADMIN} - 중간 관리자 역할</li>
 * </ul>
 */
@Getter
@RequiredArgsConstructor
public enum RoleName {

  /**
   * 시스템 관리자 역할.
   * <p>
   * 이 역할은 시스템의 모든 기능에 대한 접근 권한을 가집니다.
   * </p>
   */
  ADMIN,

  /**
   * 일반 사용자 역할.
   * <p>
   * 이 역할은 기본적인 시스템 기능에 대한 접근 권한을 가집니다.
   * </p>
   */
  USER,

  /**
   * 중간 관리자 역할.
   * <p>
   * 이 역할은 일반 사용자보다 더 많은 권한을 가지지만, 시스템 관리자보다는 적은 권한을 가집니다.
   * </p>
   */
  INTERMEDIATE_ADMIN
}
