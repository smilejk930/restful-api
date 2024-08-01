package kr.app.restfulapi.domain.common.user.gnrl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.user.gnrl.entity.UserAuthrt;
import kr.app.restfulapi.domain.common.user.gnrl.entity.UserAuthrtId;

@Repository
public interface UserAuthrtRepository extends JpaRepository<UserAuthrt, UserAuthrtId> {

}
