package kr.app.restfulapi.domain.common.code.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.code.entity.CdGroup;

@Repository
public interface CdGroupRepository extends JpaRepository<CdGroup, String>, CdGroupRepositoryCustom {

  Optional<CdGroup> findByCdGroupNmAndUseYn(String cdGroupNm, String useYn);

  Optional<CdGroup> findByCdGroupNm(String cdGroupNm);
}
