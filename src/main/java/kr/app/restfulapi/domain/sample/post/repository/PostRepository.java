package kr.app.restfulapi.domain.sample.post.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.sample.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String>, PostRepositoryCustom {

  Optional<Post> findByPostTsidAndDelYn(String postTsid, String delYn);

  Optional<Post> findByPostTsidAndDelYnAndRgtrTsid(String postTsid, String delYn, String rgtrTsid);
}
