package kr.app.restfulapi.domain.common.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.user.dto.UserDto;
import kr.app.restfulapi.domain.common.user.entity.User;
import kr.app.restfulapi.domain.common.user.repository.UserRepository;
import kr.app.restfulapi.global.jwt.JwtSecurityConfig;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final JwtSecurityConfig jwtSecurityConfig;

  @Transactional
  public UserDto createUser(UserDto userDto) {

    User user = userDto.toEntity();
    user.setPassword("{bcrypt}" + jwtSecurityConfig.passwordEncoder().encode(user.getPassword()));
    User savedUser = userRepository.save(user);

    return UserDto.toDto(savedUser);
  }
}