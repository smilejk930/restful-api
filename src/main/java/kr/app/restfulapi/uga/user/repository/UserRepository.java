package kr.app.restfulapi.uga.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.uga.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // Optional<User> findByUsername(String username);
}
