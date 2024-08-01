package kr.app.restfulapi.domain.admin.code.controller;

import java.util.List;
import java.util.Optional;
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
import kr.app.restfulapi.domain.admin.code.dto.CdMngReqstDto;
import kr.app.restfulapi.domain.admin.code.dto.CdMngRspnsDto;
import kr.app.restfulapi.domain.admin.code.dto.CdMngSrchDto;
import kr.app.restfulapi.domain.admin.code.service.CdMngService;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/code/cds")
public class CdMngController {

  private final CdMngService cdMngService;

  @GetMapping("/{cdGroupNm}")
  public ResponseEntity<SuccessResponse> getAllCdByCdGroupNm(@PathVariable String cdGroupNm, @ModelAttribute CdMngSrchDto srchDto) {

    List<CdMngRspnsDto> cdMngRspnsDtoList = cdMngService.getAllCdByCdGroupNm(cdGroupNm, srchDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(cdMngRspnsDtoList).build());
  }

  @PostMapping("/{cdGroupNm}")
  public ResponseEntity<SuccessResponse> createCdByCd(@PathVariable String cdGroupNm, @Validated
  @RequestBody CdMngReqstDto cdMngReqstDto) {
    CdMngRspnsDto cdMngRspnsDto = cdMngService.createCd(cdGroupNm, cdMngReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.CREATED).data(cdMngRspnsDto).build());
  }

  @PutMapping("/{cdGroupNm}/{cdNm}")
  public ResponseEntity<SuccessResponse> updateCd(@PathVariable String cdGroupNm, @PathVariable String cdNm, @Validated
  @RequestBody CdMngReqstDto cdMngReqstDto) {

    Optional<CdMngRspnsDto> optCdMngRspnsDto = cdMngService.updateCd(cdGroupNm, cdNm, cdMngReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.UPDATED).data(optCdMngRspnsDto).build());
  }
}
