package kr.app.restfulapi.domain.common.user.gnrl.service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.user.gnrl.dto.GnrlUserReqstDto;
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
  public Optional<GnrlUserReqstDto> getGnrlUserByLgnId(String lgnId) {

    Optional<GnrlUserReqstDto> optGnrlUserDto = gnrlUserRepository.findByLgnId(lgnId).map(GnrlUserReqstDto::toDto);

    return optGnrlUserDto.map(Optional::of).orElseThrow(() -> new ResourceNotFoundException("lgnId", lgnId, FieldErrorReason.USER_NOT_FOUND));
  }

  @Transactional
  public GnrlUserReqstDto createGnrlUser(GnrlUserReqstDto gnrlUserReqstDto) {

    if (gnrlUserRepository.findByLgnId(gnrlUserReqstDto.lgnId()).isPresent()) {
      throw new LoginIdAlreadyExistsException(gnrlUserReqstDto.lgnId());
    }
    if (!isValidPassword(gnrlUserReqstDto.pswd())) {
      throw new InvalidPasswordException(gnrlUserReqstDto.pswd());
    }

    GnrlUser gnrlUser = gnrlUserReqstDto.toEntity();
    gnrlUser.setPswd(passwordEncoder.encode(gnrlUser.getPswd()));
    GnrlUser savedGnrlUser = gnrlUserRepository.save(gnrlUser);

    return GnrlUserReqstDto.toDto(savedGnrlUser);
  }

  // TODO 비밀번호 정책 검증 로직
  private boolean isValidPassword(String pswd) {
    return pswd != null && pswd.length() >= 8 && pswd.matches(".*[A-Z].*") && pswd.matches(".*[a-z].*") && pswd.matches(".*\\d.*");
  }
}
