package kr.app.restfulapi.domain.admin.menu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.admin.code.dto.CdMngReqstDto;
import kr.app.restfulapi.domain.admin.menu.dto.MenuMngRspnsDto;
import kr.app.restfulapi.domain.common.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuMngService {

  private final MenuRepository menuRepository;

  @Transactional
  public MenuMngRspnsDto createMenu(CdMngReqstDto cdMngReqstDto) {

    // httpDmndMethNm와 urlAddr가 같은 것이 있는 지 확인
    // 저장


    return MenuMngRspnsDto.toDto(null, null);
  }
}
