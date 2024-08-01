package kr.app.restfulapi.domain.portal.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import kr.app.restfulapi.domain.common.user.gnrl.dto.GnrlUserReqstDto;
import kr.app.restfulapi.domain.common.user.gnrl.service.GnrlUserService;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원가입/탈퇴 관련 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserAccountController {

  private final GnrlUserService gnrlUserService;

  @PostMapping("/signup")
  public ResponseEntity<SuccessResponse> signupGnrlUser(@Valid
  @RequestBody GnrlUserReqstDto gnrlUserReqstDto) {
    GnrlUserReqstDto createdGnrlUserDto = gnrlUserService.createGnrlUser(gnrlUserReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.CREATED).data(createdGnrlUserDto).build());
  }

  @PostMapping("/unregister")
  public ResponseEntity<SuccessResponse> unregisterGnrlUser(@Valid
  @RequestBody GnrlUserReqstDto gnrlUserReqstDto) {
    // TODO 회원탈퇴 기능 구현

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(null).build());
  }

}
