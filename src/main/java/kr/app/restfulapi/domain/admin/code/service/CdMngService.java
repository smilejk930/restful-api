package kr.app.restfulapi.domain.admin.code.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.admin.code.dto.CdMngReqstDto;
import kr.app.restfulapi.domain.admin.code.dto.CdMngRspnsDto;
import kr.app.restfulapi.domain.admin.code.dto.CdMngSrchDto;
import kr.app.restfulapi.domain.common.code.entity.Cd;
import kr.app.restfulapi.domain.common.code.entity.CdGroup;
import kr.app.restfulapi.domain.common.code.repository.CdGroupRepository;
import kr.app.restfulapi.domain.common.code.repository.CdRepository;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CdMngService {

  private final CdRepository cdRepository;
  private final CdGroupRepository cdGroupRepository;

  @Transactional(readOnly = true)
  public List<CdMngRspnsDto> getAllCdByCdGroupNmAndWithCriteria(String cdGroupNm, CdMngSrchDto srchDto) {

    return cdRepository.findAllWithCriteria(cdGroupNm, srchDto.toEntity()).stream().map(CdMngRspnsDto::toDto).toList();
  }

  @Transactional(readOnly = true)
  public List<CdMngRspnsDto> getAllCdByCdGroupNm(String cdGroupNm) {

    Sort sort = Sort.by(Sort.Order.asc("cdSeq"));
    return cdRepository.findAllByCdGroupNm(cdGroupNm, sort).stream().map(CdMngRspnsDto::toDto).toList();
  }

  @Transactional
  public CdMngRspnsDto createCd(String cdGroupNm, CdMngReqstDto cdMngReqstDto) {

    CdGroup cdGroup = cdGroupRepository.findByCdGroupNm(cdGroupNm).orElseThrow(ResourceNotFoundException::new);

    Sort sort = Sort.by(Sort.Order.desc("cdNm"));
    List<Cd> cdList = cdRepository.findAllByCdGroupNm(cdGroupNm, sort);

    String cdSeNm = cdGroup.getCdSeNm();
    String cdNm = generateCdNm(cdSeNm, cdList);

    Cd saveCd = cdMngReqstDto.toEntity();
    saveCd.setCdGroupNm(cdGroupNm);
    saveCd.setCdNm(cdNm);
    saveCd.setCdGroup(cdGroup); // Cd 엔티티와 CdGroup 엔티티 간의 관계를 설정, JPA 관계 매핑(JPA가 엔티티 간의 관계를 올바르게 인식하고 관리할 수 있도록 함)

    Cd savedCd = cdRepository.save(saveCd);

    return CdMngRspnsDto.toDto(savedCd);
  }

  @Transactional
  public Optional<CdMngRspnsDto> updateCd(String cdGroupNm, String cdNm, CdMngReqstDto cdMngReqstDto) {

    Cd cd = cdRepository.findByCdGroupNmAndCdNm(cdGroupNm, cdNm).orElseThrow(ResourceNotFoundException::new);

    cd.setCdKornNm(cdMngReqstDto.cdKornNm());
    cd.setUpCdNm(cdMngReqstDto.upCdNm());
    cd.setCdSeq(cdMngReqstDto.cdSeq());
    cd.setCdExpln(cdMngReqstDto.cdExpln());
    cd.setUseYn(cdMngReqstDto.useYn());

    Cd updatedCd = cdRepository.save(cd);

    return Optional.of(updatedCd).map(CdMngRspnsDto::toDto);
  }

  /* cdNm 생성로직 */
  private String generateCdNm(String cdSeNm, List<Cd> cdList) {
    if (cdList.isEmpty()) {
      return cdSeNm + String.format("%03d", 1);
    } else {
      String lastCdNm = cdList.getFirst().getCdNm();
      String lastCdNumberStr = lastCdNm.substring(cdSeNm.length());
      Integer lastCdNumber = Integer.parseInt(lastCdNumberStr);
      lastCdNumber++;

      // 3자리 형식으로 포맷팅 (001, 002, ..., 999)
      return cdSeNm + String.format("%03d", lastCdNumber);
    }
  }
}
