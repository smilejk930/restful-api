package kr.app.restfulapi.domain.common.code.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.code.dto.CdRspnsDto;
import kr.app.restfulapi.domain.common.code.repository.CdGroupRepository;
import kr.app.restfulapi.domain.common.code.repository.CdRepository;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CdService {

  private final CdGroupRepository cdGroupRepository;
  private final CdRepository cdRepository;

  @Transactional(readOnly = true)
  public List<CdRspnsDto> getAllCdByCdGroupNm(String cdGroupNm) {
    return cdGroupRepository.findByCdGroupNmAndUseYn(cdGroupNm, "Y")
        .map(cdGroup -> cdGroup.getCds().stream().map(CdRspnsDto::toDto).toList())
        .orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public List<CdRspnsDto> getAllCdByCdGroupNmAndUpCdNm(String cdGroupNm, String upCdNm) {

    Sort sort = Sort.by(Sort.Direction.ASC, "cdSeq");
    List<CdRspnsDto> cdRspnsDtoList =
        cdRepository.findAllByCdGroupNmAndUpCdNmAndUseYn(cdGroupNm, upCdNm, "Y", sort).stream().map(CdRspnsDto::toDto).toList();

    if (cdRspnsDtoList.isEmpty()) {
      throw new ResourceNotFoundException();
    }

    return cdRspnsDtoList;
  }

  @Transactional(readOnly = true)
  public Optional<CdRspnsDto> getCdByCdGroupNmAndCdNm(String cdGroupNm, String cdNm) {

    Optional<CdRspnsDto> optCdRspnsDto = cdRepository.findByCdGroupNmAndCdNmAndUseYn(cdGroupNm, cdNm, "Y").map(CdRspnsDto::toDto);;

    return optCdRspnsDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }
}
