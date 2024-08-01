package kr.app.restfulapi.domain.common.code.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.code.entity.Cd;
import kr.app.restfulapi.domain.common.code.entity.CdId;

@Repository
public interface CdRepository extends JpaRepository<Cd, CdId>, CdRepositoryCustom {

  List<Cd> findAllByCdGroupNm(String cdGroupNm, Sort sort);

  List<Cd> findAllByCdGroupNmAndUseYn(String cdGroupNm, String useYn, Sort sort);

  List<Cd> findAllByCdGroupNmAndUpCdNm(String cdGroupNm, String upCdNm, Sort sort);

  List<Cd> findAllByCdGroupNmAndUpCdNmAndUseYn(String cdGroupNm, String upCdNm, String useYn, Sort sort);

  Optional<Cd> findByCdGroupNmAndCdNm(String cdGroupNm, String cdNm);

  Optional<Cd> findByCdGroupNmAndCdNmAndUseYn(String cdGroupNm, String cdNm, String useYn);
}
