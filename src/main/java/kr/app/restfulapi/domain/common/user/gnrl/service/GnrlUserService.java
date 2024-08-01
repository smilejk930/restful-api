package kr.app.restfulapi.domain.common.user.gnrl.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.hypersistence.tsid.TSID;
import kr.app.restfulapi.domain.common.user.gnrl.dto.GnrlUserReqstDto;
import kr.app.restfulapi.domain.common.user.gnrl.dto.GnrlUserRspnsDto;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;
import kr.app.restfulapi.domain.common.user.gnrl.repository.GnrlUserRepository;
import kr.app.restfulapi.domain.common.user.gnrl.repository.UserAuthrtRepository;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserAcntSttsType;
import kr.app.restfulapi.global.response.error.FieldErrorReason;
import kr.app.restfulapi.global.response.error.exception.InvalidPasswordException;
import kr.app.restfulapi.global.response.error.exception.LoginIdAlreadyExistsException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GnrlUserService {

  private final GnrlUserRepository gnrlUserRepository;
  private final UserAuthrtRepository userAuthrtRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public Optional<GnrlUserRspnsDto> getGnrlUserByLgnId(String lgnId) {

    Optional<GnrlUserRspnsDto> optGnrlUserRspnsDto = gnrlUserRepository.findByLgnId(lgnId).map(GnrlUserRspnsDto::toDto);

    return optGnrlUserRspnsDto.map(Optional::of).orElseThrow(() -> new ResourceNotFoundException("lgnId", lgnId, FieldErrorReason.USER_NOT_FOUND));
  }

  @Transactional
  public GnrlUserRspnsDto createGnrlUser(GnrlUserReqstDto gnrlUserReqstDto) {

    if (gnrlUserRepository.findByLgnId(gnrlUserReqstDto.lgnId()).isPresent()) {
      throw new LoginIdAlreadyExistsException(gnrlUserReqstDto.lgnId());
    }
    if (!isValidPassword(gnrlUserReqstDto.pswd())) {
      throw new InvalidPasswordException(gnrlUserReqstDto.pswd());
    }

    GnrlUser gnrlUser = gnrlUserReqstDto.toEntity();

    gnrlUser.setUserTsid(TSID.fast().toString());
    gnrlUser.setRgtrTsid(gnrlUser.getUserTsid());
    gnrlUser.setPswd(passwordEncoder.encode(gnrlUser.getPswd()));
    gnrlUser.setUserAcntSttsCd(UserAcntSttsType.UAS001);// TODO 추후 사용자 가입할 때 UAS002로 변경해야함
    gnrlUser.setJoinDt(LocalDateTime.now());
    GnrlUser savedGnrlUser = gnrlUserRepository.save(gnrlUser);

    // 사용자유형 등록
    gnrlUserReqstDto.toUserAuthrtEntities(savedGnrlUser).forEach(userAuthrt -> {
      userAuthrt.setRgtrTsid(userAuthrt.getUserTsid());
      userAuthrtRepository.save(userAuthrt);
    });

    return GnrlUserRspnsDto.toDto(savedGnrlUser);
  }

  // TODO 비밀번호 정책 검증 로직
  private boolean isValidPassword(String pswd) {
    return pswd != null && pswd.length() >= 8 && pswd.matches(".*[A-Z].*") && pswd.matches(".*[a-z].*") && pswd.matches(".*\\d.*");
  }
}
