package kr.app.restfulapi.domain.admin.menu.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.domain.admin.menu.dto.MenuMngReqstDto;
import kr.app.restfulapi.domain.admin.menu.dto.MenuMngRspnsDto;
import kr.app.restfulapi.domain.common.menu.entity.Menu;
import kr.app.restfulapi.domain.common.menu.entity.MenuAuthrt;
import kr.app.restfulapi.domain.common.menu.repository.MenuAuthrtRepository;
import kr.app.restfulapi.domain.common.menu.repository.MenuRepository;
import kr.app.restfulapi.global.response.error.exception.DuplicateKeyException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuMngService {

  private final MenuRepository menuRepository;
  private final MenuAuthrtRepository menuAuthrtRepository;

  @Transactional
  public MenuMngRspnsDto createMenu(MenuMngReqstDto menuMngReqstDto) {
    if (menuRepository.findByHttpDmndMethNmAndUrlAddr(menuMngReqstDto.httpDmndMethNm(), menuMngReqstDto.urlAddr()).isPresent()) {
      throw new DuplicateKeyException("입력한 HTTP요청메소드명과 URL주소 존재합니다.");
    }

    Menu savedMenu = menuRepository.save(menuMngReqstDto.toEntity());

    // 메뉴권한에 사용자유형 등록
    List<MenuAuthrt> menuAuthrts = menuMngReqstDto.toMenuAuthrtEntities(savedMenu);
    menuAuthrts.forEach(menuAuthrtRepository::save);

    return MenuMngRspnsDto.toDto(savedMenu, menuAuthrts);
  }
}
