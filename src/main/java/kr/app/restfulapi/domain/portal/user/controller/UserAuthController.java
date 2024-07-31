package kr.app.restfulapi.domain.portal.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.app.restfulapi.domain.common.user.auth.dto.JwtRspnsDto;
import kr.app.restfulapi.domain.common.user.auth.dto.LgnReqstDto;
import kr.app.restfulapi.global.response.error.FieldErrorReason;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import kr.app.restfulapi.global.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인/로그아웃 관련 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserAuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;

  @PostMapping("/login")
  public ResponseEntity<SuccessResponse> loginGnrlUser(@Validated @RequestBody LgnReqstDto lgnReqstDto) throws Exception {
    try {

      // AuthenticationManager를 사용하여 인증 수행
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(lgnReqstDto.lgnId(), lgnReqstDto.pswd()));

      // 인증 성공 시 SecurityContext에 인증 정보 저장
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = tokenProvider.generateToken(authentication);
      log.info("User logged in successfully: {}", lgnReqstDto.lgnId());
      return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(new JwtRspnsDto(jwt)).build());
    } catch (BadCredentialsException ex) {
      log.warn("Login attempt failed for user: {}", lgnReqstDto.lgnId());
      throw new BadCredentialsException(FieldErrorReason.BAD_CREDENTIALS.getReason());
    } catch (LockedException ex) {
      log.warn("Locked account attempt to login: {}", lgnReqstDto.lgnId());
      throw new LockedException("Account is locked");
    } catch (Exception ex) {
      throw new Exception("An error occurred during login");
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<SuccessResponse> logoutGnrlUser(@Validated @RequestBody LgnReqstDto lgnReqstDto) throws Exception {
    // TODO LOGOUT 구현
    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(null).build());
  }
}
