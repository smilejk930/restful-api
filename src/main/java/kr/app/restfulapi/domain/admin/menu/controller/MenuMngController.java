package kr.app.restfulapi.domain.admin.menu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.app.restfulapi.domain.admin.menu.dto.MenuMngReqstDto;
import kr.app.restfulapi.domain.admin.menu.dto.MenuMngRspnsDto;
import kr.app.restfulapi.domain.admin.menu.service.MenuMngService;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/menu")
public class MenuMngController {

  private final MenuMngService menuMngService;

  @PostMapping
  public ResponseEntity<SuccessResponse> createMenu(@Validated
  @RequestBody MenuMngReqstDto menuMngReqstDto) {
    MenuMngRspnsDto menuMngRspnsDto = menuMngService.createMenu(menuMngReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.CREATED).data(menuMngRspnsDto).build());
  }

}
