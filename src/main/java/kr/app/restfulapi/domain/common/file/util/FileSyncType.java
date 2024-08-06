package kr.app.restfulapi.domain.common.file.util;

/**
 * <pre>
 * 파일동기화
 * 망연계 파일동기화 시 파일의 동기화 상태를 표시
 * </pre>
 */
public enum FileSyncType {
  /** 동기화 미완료 */
  FSC001,
  /** 망연계서버 전송완료 */
  FSC002,
  /** 동기화 완료 */
  FSC003
}
