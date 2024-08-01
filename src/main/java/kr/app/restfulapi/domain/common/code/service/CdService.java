package kr.app.restfulapi.domain.common.code.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.jsonwebtoken.lang.Collections;
import kr.app.restfulapi.domain.common.code.dto.CdRspnsDto;
import kr.app.restfulapi.domain.common.code.repository.CdGroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CdService {

  private final CdGroupRepository cdGroupRepository;

  @Transactional(readOnly = true)
  public List<CdRspnsDto> getAllByCdGroupNm(String cdGroupNm) {
    return cdGroupRepository.findByCdGroupNmAndUseYn(cdGroupNm, "Y")
        .map(cdGroup -> cdGroup.getCds().stream().map(CdRspnsDto::toDto).toList())
        .orElse(Collections.emptyList());
  }
}
