package kr.app.restfulapi.uga.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.uga.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
  // 추가적인 커스텀 메소드가 필요하다면 여기에 정의
}
