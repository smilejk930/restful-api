package kr.app.restfulapi.domain.common.resource.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourceAccessType {
  MEMBERS,
  NON_MEMBERS,
  PERMISSION_REQUIRED
}
