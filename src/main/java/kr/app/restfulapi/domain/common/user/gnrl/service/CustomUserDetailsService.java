package kr.app.restfulapi.domain.common.user.gnrl.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;
import kr.app.restfulapi.domain.common.user.gnrl.repository.GnrlUserRepository;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserPrincipal;
import lombok.RequiredArgsConstructor;

/**
 * 사용자 인증을 위한 커스텀 UserDetailsService 구현체입니다.
 * 이 서비스는 Spring Security의 인증 메커니즘과 통합되어 사용자 정보를 로드합니다.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final GnrlUserRepository gnrlUserRepository;

  /**
   * 주어진 로그인 아이디로 사용자를 조회하고 UserDetails 객체를 반환합니다.
   *
   * @param lgnId 조회할 사용자의 로그인 아이디
   * @return 조회된 사용자 정보를 담고 있는 UserDetails 객체
   * @throws UsernameNotFoundException 주어진 로그인 아이디에 해당하는 사용자가 없을 경우 발생
   */
  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String lgnId) throws UsernameNotFoundException {
    Optional<GnrlUser> optGnrlUser = gnrlUserRepository.findByLgnId(lgnId);

    return optGnrlUser.map(UserPrincipal::create).orElseThrow(() -> new UsernameNotFoundException("로그인 아이디 " + lgnId + "가 존재하지 않습니다."));
  }
}
