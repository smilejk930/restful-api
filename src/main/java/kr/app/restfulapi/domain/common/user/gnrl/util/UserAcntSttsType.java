package kr.app.restfulapi.domain.common.user.gnrl.util;

/**
 * <pre>
 * 사용자계정상태
 * 사용자계정의 상태
 * </pre>
 */
public enum UserAcntSttsType {
  /** 정상 */
  UAS001,
  /** 사용자가입대기 */
  UAS002,
  /** 소속업체변경신청 */
  UAS003,
  /** 탈퇴 */
  UAS004,
  /** 임시사용자 */
  UAS005
}
