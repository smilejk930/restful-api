package kr.app.restfulapi.domain.common.user.gnrl.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;


@Repository
public interface GnrlUserRepository extends JpaRepository<GnrlUser, String> {

  Optional<GnrlUser> findByLgnId(String lgnId);

  Optional<GnrlUser> findByUserNm(String userNm);

  Optional<GnrlUser> findByUserTsid(String userTsid);
}
