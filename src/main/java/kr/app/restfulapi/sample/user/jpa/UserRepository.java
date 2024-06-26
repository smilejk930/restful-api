package kr.app.restfulapi.sample.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.sample.user.User;

@Repository("sampleUserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

}
