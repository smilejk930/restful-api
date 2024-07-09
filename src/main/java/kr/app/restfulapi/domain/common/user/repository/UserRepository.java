package kr.app.restfulapi.domain.common.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.user.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByLoginId(String loginId);
}
