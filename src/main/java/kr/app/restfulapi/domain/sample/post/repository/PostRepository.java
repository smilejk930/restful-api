package kr.app.restfulapi.uga.post.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.uga.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String>, PostRepositoryCustom {
  Optional<Post> findByPostIdAndDeleteAt(String postId, String deleteAt);
}
