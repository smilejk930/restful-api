package kr.app.restfulapi.domain.common.code.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.common.code.dto.GroupCdRspnsDto;
import kr.app.restfulapi.domain.common.code.dto.GroupCdSrchDto;
import kr.app.restfulapi.domain.common.code.entity.GroupCd;
import kr.app.restfulapi.domain.common.code.repository.GroupCdRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupCdService {

  private final GroupCdRepository groupCdRepository;

  @Transactional(readOnly = true)
  public List<GroupCdRspnsDto> getAllGroupCd(GroupCdSrchDto groupCdSrchDto) {

    Optional<GroupCd> optGroupCd = groupCdRepository.findByCdGroupNmAndUseYn(groupCdSrchDto.cdGroupNm(), "Y");
    return null;
  }
}
