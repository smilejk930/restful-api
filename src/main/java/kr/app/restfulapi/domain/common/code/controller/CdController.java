package kr.app.restfulapi.domain.common.code.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.app.restfulapi.domain.common.code.dto.CdRspnsDto;
import kr.app.restfulapi.domain.common.code.service.CdService;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common/code/cds")
public class CdController {

  private final CdService cdService;

  @GetMapping("/{cdGroupNm}")
  public ResponseEntity<SuccessResponse> getAllCdByCdGroupNm(@PathVariable String cdGroupNm) {

    List<CdRspnsDto> cdRspnsDtos = cdService.getAllCdByCdGroupNm(cdGroupNm);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(cdRspnsDtos).build());
  }

  @GetMapping("/{cdGroupNm}/upCdNms/{upCdNm}")
  public ResponseEntity<SuccessResponse> getAllCdByCdGroupNmAndUpCdNm(@PathVariable String cdGroupNm, @PathVariable String upCdNm) {

    List<CdRspnsDto> cdRspnsDtos = cdService.getAllCdByCdGroupNmAndUpCdNm(cdGroupNm, upCdNm);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(cdRspnsDtos).build());
  }

  @GetMapping("/{cdGroupNm}/{cdNm}")
  public ResponseEntity<SuccessResponse> getCdByCdGroupNmAndCdNm(@PathVariable String cdGroupNm, @PathVariable String cdNm) {

    Optional<CdRspnsDto> optCdRspnsDto = cdService.getCdByCdGroupNmAndCdNm(cdGroupNm, cdNm);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(optCdRspnsDto).build());
  }
}
