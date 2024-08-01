package kr.app.restfulapi.domain.admin.code.controller;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.app.restfulapi.domain.admin.code.dto.CdGroupMngReqstDto;
import kr.app.restfulapi.domain.admin.code.dto.CdGroupMngRspnsDto;
import kr.app.restfulapi.domain.admin.code.dto.CdGroupMngSrchDto;
import kr.app.restfulapi.domain.admin.code.service.CdMngService;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import kr.app.restfulapi.global.validation.ValidationGroups.Create;
import kr.app.restfulapi.global.validation.ValidationGroups.Update;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/code/cds")
public class CdMngController {

  private final CdMngService cdMngService;

  @GetMapping("/{postTsid}")
  public ResponseEntity<SuccessResponse> getAllCd(@ModelAttribute CdGroupMngSrchDto srchDto,
      @PageableDefault(size = 10, sort = "cdGroupNm", direction = Sort.Direction.DESC) Pageable pageable) {

    Page<CdGroupMngRspnsDto> cdGroupMngRspnsDtoList = cdMngService.getAllCdGroup(srchDto, pageable);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(cdGroupMngRspnsDtoList).build());
  }

  @PostMapping
  public ResponseEntity<SuccessResponse> createCdGroup(@Validated(Create.class)
  @RequestBody CdGroupMngReqstDto cdGroupMngReqstDto) {
    CdGroupMngRspnsDto cdGroupMngRspnsDto = cdMngService.createCdGroup(cdGroupMngReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.CREATED).data(cdGroupMngRspnsDto).build());
  }

  @PutMapping("/{cdGroupNm}")
  public ResponseEntity<SuccessResponse> updateCdGroup(@PathVariable String cdGroupNm, @Validated(Update.class)
  @RequestBody CdGroupMngReqstDto cdGroupMngReqstDto) {

    Optional<CdGroupMngRspnsDto> optCdGroupMngRspnsDto = cdMngService.updateCdGroup(cdGroupNm, cdGroupMngReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.UPDATED).data(optCdGroupMngRspnsDto).build());
  }
}
