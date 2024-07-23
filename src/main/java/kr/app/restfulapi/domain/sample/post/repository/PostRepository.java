package kr.app.restfulapi.domain.sample.post.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.sample.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String>, PostRepositoryCustom {

  Optional<Post> findByPostIdAndDeleteAtAndRegisterId(String postId, String deleteAt, String registerId);
}
