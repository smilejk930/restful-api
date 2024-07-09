package kr.app.restfulapi.global.auditor;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    // @CreatedBy, @LastModifiedBy 값 주입
    // TODO JWT 구현 시 토큰으로 인한 userDetails 정보 가져오기
    return Optional.ofNullable("1");
  }

}
