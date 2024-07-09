package kr.app.restfulapi.domain.common.auth.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.user.dto.UserDto;
import kr.app.restfulapi.domain.common.user.repository.UserRepository;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public Optional<UserDto> getUserByLoginId(String loginId) {

    Optional<UserDto> optUserDto = userRepository.findByLoginId(loginId).map(UserDto::toDto);

    return optUserDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }
}
