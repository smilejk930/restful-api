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
import kr.app.restfulapi.domain.common.auth.dto.JwtResponseDto;
import kr.app.restfulapi.domain.common.auth.dto.LoginDto;
import kr.app.restfulapi.domain.common.user.dto.UserDto;
import kr.app.restfulapi.domain.common.user.service.UserService;
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
  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<SuccessResponse> authenticateUser(@Validated @RequestBody LoginDto loginDto) throws Exception {
    try {

      // AuthenticationManager를 사용하여 인증 수행
      Authentication authentication =
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.loginId(), loginDto.password()));

      // 인증 성공 시 SecurityContext에 인증 정보 저장
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = tokenProvider.generateToken(authentication);
      log.info("User logged in successfully: {}", loginDto.loginId());
      return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(new JwtResponseDto(jwt)).build());
    } catch (BadCredentialsException ex) {
      log.warn("Login attempt failed for user: {}", loginDto.loginId());
      throw new BadCredentialsException(FieldErrorReason.BAD_CREDENTIALS.getReason());
    } catch (LockedException ex) {
      log.warn("Locked account attempt to login: {}", loginDto.loginId());
      throw new LockedException("Account is locked");
    } catch (Exception ex) {
      throw new Exception("An error occurred during login");
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<SuccessResponse> createUser(@Valid @RequestBody UserDto userDto) {
    UserDto createdUserDto = userService.createUser(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder().status(SuccessStatus.CREATED).data(createdUserDto).build());
  }
}
