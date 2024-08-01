package kr.app.restfulapi.domain.common.user.gnrl.util;

/**
 * <pre>
 * 사용자유형
 * 사용자의 권한제어
 * </pre>
 */
public enum UserType {
  /** 관리자 */
  USR000,
  /** 유지보수사업자 */
  USR001,
  /** 화물운송사업자 */
  USR003,
  /** 버스운송사업자 */
  USR004,
  /** 택시운송사업자 */
  USR005,
  /** 가맹점(주유/충전소) */
  USR006
}
