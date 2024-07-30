package kr.app.restfulapi.domain.common.auth.controller;

import org.springframework.http.HttpStatus;
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
import jakarta.validation.Valid;
import kr.app.restfulapi.domain.common.auth.dto.JwtRspnsDto;
import kr.app.restfulapi.domain.common.auth.dto.LgnDto;
import kr.app.restfulapi.domain.common.user.gnrl.dto.GnrlUserDto;
import kr.app.restfulapi.domain.common.user.gnrl.service.GnrlUserService;
import kr.app.restfulapi.global.response.error.FieldErrorReason;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import kr.app.restfulapi.global.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthContorller {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;
  private final GnrlUserService gnrlUserService;

  @PostMapping("/login")
  public ResponseEntity<SuccessResponse> authenticateGnrlUser(@Validated @RequestBody LgnDto lgnDto) throws Exception {
    try {

      // AuthenticationManager를 사용하여 인증 수행
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(lgnDto.lgnId(), lgnDto.pswd()));

      // 인증 성공 시 SecurityContext에 인증 정보 저장
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = tokenProvider.generateToken(authentication);
      log.info("User logged in successfully: {}", lgnDto.lgnId());
      return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(new JwtRspnsDto(jwt)).build());
    } catch (BadCredentialsException ex) {
      log.warn("Login attempt failed for user: {}", lgnDto.lgnId());
      throw new BadCredentialsException(FieldErrorReason.BAD_CREDENTIALS.getReason());
    } catch (LockedException ex) {
      log.warn("Locked account attempt to login: {}", lgnDto.lgnId());
      throw new LockedException("Account is locked");
    } catch (Exception ex) {
      throw new Exception("An error occurred during login");
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<SuccessResponse> createGnrlUser(@Valid @RequestBody GnrlUserDto gnrlUserDto) {
    GnrlUserDto createdGnrlUserDto = gnrlUserService.createGnrlUser(gnrlUserDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder().status(SuccessStatus.CREATED).data(createdGnrlUserDto).build());
  }
}
