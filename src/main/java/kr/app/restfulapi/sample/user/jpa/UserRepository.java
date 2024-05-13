package kr.app.restfulapi.sample.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.app.restfulapi.sample.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
