package kr.app.restfulapi.domain.common.menu.util;

/**
 * <pre>
 * 메뉴접근권한
 * 사용자 접근에 대한 설정
 * </pre>
 */
public enum MenuAcsAuthrtType {
  /** 로그인+권한필요 */
  MAA001,
  /** 로그인 */
  MAA002,
  /** 비로그인+로그인 */
  MAA003
}
