package kr.app.restfulapi.domain.common.user.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import kr.app.restfulapi.domain.common.user.entity.User;
import kr.app.restfulapi.domain.common.user.repository.UserRepository;
import kr.app.restfulapi.domain.common.user.util.UserPrincipal;
import kr.app.restfulapi.global.response.error.FieldErrorReason;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userNm) throws UsernameNotFoundException {
    throw new UnsupportedOperationException("loadUserByUsername is not supported. Use loadUserByLoginId instead.");
  }

  public UserDetails loadUserByLoginId(String loginId) {
    Optional<User> optUser = userRepository.findByLoginId(loginId);

    if (optUser.isPresent()) {
      return UserPrincipal.create(optUser.get());
    } else {
      throw new ResourceNotFoundException("loginId", loginId, FieldErrorReason.USER_NOT_FOUND);
    }
  }
}
