package kr.app.restfulapi.domain.common.code.repository;

import java.util.List;
import kr.app.restfulapi.domain.common.code.entity.Cd;

public interface CdRepositoryCustom {

  List<Cd> findAllWithCriteria(String cdGroupNm, Cd cd);
}
