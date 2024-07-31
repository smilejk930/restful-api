package kr.app.restfulapi.domain.common.code.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.code.entity.GroupCd;

@Repository
public interface GroupCdRepository extends JpaRepository<GroupCd, String> {

  Optional<GroupCd> findByCdGroupNmAndUseYn(String cdGroupNm, String useYn);
}
