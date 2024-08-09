package kr.app.restfulapi.domain.admin.code.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.admin.code.dto.CdGroupMngReqstDto;
import kr.app.restfulapi.domain.admin.code.dto.CdGroupMngRspnsDto;
import kr.app.restfulapi.domain.admin.code.dto.CdGroupMngSrchDto;
import kr.app.restfulapi.domain.common.code.entity.CdGroup;
import kr.app.restfulapi.domain.common.code.repository.CdGroupRepository;
import kr.app.restfulapi.global.response.error.exception.DuplicateKeyException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CdGroupMngService {

  private final CdGroupRepository cdGroupRepository;

  @Transactional(readOnly = true)
  public Page<CdGroupMngRspnsDto> getAllCdGroup(CdGroupMngSrchDto srchDto, Pageable pageable) {

    return cdGroupRepository.findAllWithCriteria(srchDto.toEntity(), pageable).map(CdGroupMngRspnsDto::toDto);
  }

  @Transactional
  public CdGroupMngRspnsDto createCdGroup(CdGroupMngReqstDto cdGroupMngReqstDto) {

    String cdGroupNm = cdGroupMngReqstDto.getCdGroupNm();

    if (cdGroupRepository.findByCdGroupNm(cdGroupNm).isPresent()) {
      throw new DuplicateKeyException("입력한 코드그룹명이 존재합니다.");
    }

    CdGroup savedCdGroup = cdGroupRepository.save(cdGroupMngReqstDto.toEntity());

    return CdGroupMngRspnsDto.toDto(savedCdGroup);
  }

  @Transactional
  public Optional<CdGroupMngRspnsDto> updateCdGroup(String cdGroupNm, CdGroupMngReqstDto cdGroupMngReqstDto) {

    Optional<CdGroupMngRspnsDto> optCdGroupMngRspnsDto = cdGroupRepository.findByCdGroupNm(cdGroupNm).map(cdGroup -> {
      cdGroup.setCdKornNm(cdGroupMngReqstDto.getCdKornNm());
      cdGroup.setUseYn(cdGroupMngReqstDto.getUseYn());
      cdGroup.setComCdYn(cdGroupMngReqstDto.getComCdYn());

      return CdGroupMngRspnsDto.toDto(cdGroup);
    });

    return optCdGroupMngRspnsDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }
}
