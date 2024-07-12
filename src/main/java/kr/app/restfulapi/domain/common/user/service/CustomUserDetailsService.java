package kr.app.restfulapi.domain.common.user.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import kr.app.restfulapi.domain.common.user.dto.UserDto;
import kr.app.restfulapi.domain.common.user.util.UserPrincipal;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String userNm) throws UsernameNotFoundException {
    throw new UnsupportedOperationException("loadUserByUsername is not supported. Use loadUserByLoginId instead.");
  }

  public UserDetails loadUserByLoginId(String loginId) {
    Optional<UserDto> optUserDto = userService.getUserByLoginId(loginId);

    return UserPrincipal.create(optUserDto.get().toEntity());
  }
}
