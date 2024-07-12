package kr.app.restfulapi.domain.common.user.service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.user.dto.UserDto;
import kr.app.restfulapi.domain.common.user.entity.User;
import kr.app.restfulapi.domain.common.user.repository.UserRepository;
import kr.app.restfulapi.global.response.error.FieldErrorReason;
import kr.app.restfulapi.global.response.error.exception.InvalidPasswordException;
import kr.app.restfulapi.global.response.error.exception.LoginIdAlreadyExistsException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public Optional<UserDto> getUserByLoginId(String loginId) {

    Optional<UserDto> optUserDto = userRepository.findByLoginId(loginId).map(UserDto::toDto);

    return optUserDto.map(Optional::of).orElseThrow(() -> new ResourceNotFoundException("loginId", loginId, FieldErrorReason.USER_NOT_FOUND));
  }

  @Transactional
  public UserDto createUser(UserDto userDto) {

    if (userRepository.findByLoginId(userDto.loginId()).isPresent()) {
      throw new LoginIdAlreadyExistsException(userDto.loginId());
    }
    if (!isValidPassword(userDto.password())) {
      throw new InvalidPasswordException(userDto.password());
    }

    User user = userDto.toEntity();
    // user.setPassword("{bcrypt}" + securityConfig.passwordEncoder().encode(user.getPassword()));
    // TODO 패스워드가 DB에 어떻게 저장되는지 확인 필요
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);

    return UserDto.toDto(savedUser);
  }

  // TODO 비밀번호 정책 검증 로직
  private boolean isValidPassword(String password) {
    return password != null && password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*")
        && password.matches(".*\\d.*");
  }
}
