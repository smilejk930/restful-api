package kr.app.restfulapi.domain.common.user.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.global.jwt.JwtSecurityConfig;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.domain.common.user.dto.UserDto;
import kr.app.restfulapi.domain.common.user.entity.User;
import kr.app.restfulapi.domain.common.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final JwtSecurityConfig jwtSecurityConfig;

  @Transactional(readOnly = true)
  public Optional<UserDto> getUserByLoginId(String loginId) {

    Optional<UserDto> optUserDto = userRepository.findByLoginId(loginId).map(UserDto::toDto);

    return optUserDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public UserDto createUser(UserDto userDto) {

    User user = userDto.toEntity();
    user.setPassword("{bcrypt}" + jwtSecurityConfig.passwordEncoder().encode(user.getPassword()));
    User savedUser = userRepository.save(user);

    return UserDto.toDto(savedUser);
  }
}
