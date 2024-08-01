package kr.app.restfulapi.domain.common.code.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import kr.app.restfulapi.domain.common.code.entity.CdGroup;

public interface CdGroupRepositoryCustom {

  Page<CdGroup> findAllWithCriteria(CdGroup cdGroup, Pageable pageable);
}
