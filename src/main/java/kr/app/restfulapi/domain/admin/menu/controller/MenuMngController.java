package kr.app.restfulapi.domain.admin.menu.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.app.restfulapi.domain.admin.code.dto.CdMngRspnsDto;
import kr.app.restfulapi.domain.admin.code.service.CdMngService;
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
  private final CdMngService cdMngService;

  @GetMapping("/menuGroups")
  public ResponseEntity<SuccessResponse> getAllCdByMenuGroupCd(@PathVariable String menuGroupCd) {

    List<CdMngRspnsDto> cdMngRspnsDtoList = cdMngService.getAllCdByCdGroupNm(menuGroupCd);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(cdMngRspnsDtoList).build());
  }

  @GetMapping("/menuGroups/{menuGroupCd}")
  public ResponseEntity<SuccessResponse> getAllMenuByMenuGroupCd(@PathVariable String menuGroupCd) {

    List<MenuMngRspnsDto> menuMngRspnsDtoList = menuMngService.getAllMenuByMenuGroupCd(menuGroupCd);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(menuMngRspnsDtoList).build());
  }

  @PostMapping("/menuGroups/{menuGroupCd}")
  public ResponseEntity<SuccessResponse> createMenu(@PathVariable String menuGroupCd, @Validated
  @RequestBody MenuMngReqstDto menuMngReqstDto) {
    MenuMngRspnsDto menuMngRspnsDto = menuMngService.createMenu(menuGroupCd, menuMngReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.CREATED).data(menuMngRspnsDto).build());
  }

  @PutMapping("/{menuTsid}")
  public ResponseEntity<SuccessResponse> updateMenu(@PathVariable String menuTsid, @Validated
  @RequestBody MenuMngReqstDto menuMngReqstDto) {
    Optional<MenuMngRspnsDto> menuMngRspnsDto = menuMngService.updateMenu(menuTsid, menuMngReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.UPDATED).data(menuMngRspnsDto).build());
  }

}
