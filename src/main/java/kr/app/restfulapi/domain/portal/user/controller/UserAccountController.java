package kr.app.restfulapi.domain.portal.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.app.restfulapi.domain.common.user.gnrl.dto.GnrlUserReqstDto;
import kr.app.restfulapi.domain.common.user.gnrl.dto.GnrlUserRspnsDto;
import kr.app.restfulapi.domain.common.user.gnrl.service.GnrlUserService;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 사용자가입/탈퇴 관련 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserAccountController {

  private final GnrlUserService gnrlUserService;

  @PostMapping("/signup")
  public ResponseEntity<SuccessResponse> signupGnrlUser(@Validated
  @RequestBody GnrlUserReqstDto gnrlUserReqstDto) {
    GnrlUserRspnsDto createdGnrlUserDto = gnrlUserService.createGnrlUser(gnrlUserReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.CREATED).data(createdGnrlUserDto).build());
  }

  @PostMapping("/unregister")
  public ResponseEntity<SuccessResponse> unregisterGnrlUser(@Validated
  @RequestBody GnrlUserReqstDto gnrlUserReqstDto) {
    // TODO 사용자탈퇴 기능 구현

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(null).build());
  }

}
