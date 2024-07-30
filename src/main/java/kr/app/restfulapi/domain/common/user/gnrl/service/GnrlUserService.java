package kr.app.restfulapi.domain.common.user.gnrl.service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.user.gnrl.dto.GnrlUserDto;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;
import kr.app.restfulapi.domain.common.user.gnrl.repository.GnrlUserRepository;
import kr.app.restfulapi.global.response.error.FieldErrorReason;
import kr.app.restfulapi.global.response.error.exception.InvalidPasswordException;
import kr.app.restfulapi.global.response.error.exception.LoginIdAlreadyExistsException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GnrlUserService {

  private final GnrlUserRepository gnrlUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public Optional<GnrlUserDto> getGnrlUserByLgnId(String lgnId) {

    Optional<GnrlUserDto> optGnrlUserDto = gnrlUserRepository.findByLgnId(lgnId).map(GnrlUserDto::toDto);

    return optGnrlUserDto.map(Optional::of).orElseThrow(() -> new ResourceNotFoundException("lgnId", lgnId, FieldErrorReason.USER_NOT_FOUND));
  }

  @Transactional
  public GnrlUserDto createGnrlUser(GnrlUserDto gnrlUserDto) {

    if (gnrlUserRepository.findByLgnId(gnrlUserDto.lgnId()).isPresent()) {
      throw new LoginIdAlreadyExistsException(gnrlUserDto.lgnId());
    }
    if (!isValidPassword(gnrlUserDto.pswd())) {
      throw new InvalidPasswordException(gnrlUserDto.pswd());
    }

    GnrlUser gnrlUser = gnrlUserDto.toEntity();
    gnrlUser.setPswd(passwordEncoder.encode(gnrlUser.getPswd()));
    GnrlUser savedGnrlUser = gnrlUserRepository.save(gnrlUser);

    return GnrlUserDto.toDto(savedGnrlUser);
  }

  // TODO 비밀번호 정책 검증 로직
  private boolean isValidPassword(String pswd) {
    return pswd != null && pswd.length() >= 8 && pswd.matches(".*[A-Z].*") && pswd.matches(".*[a-z].*") && pswd.matches(".*\\d.*");
  }
}
