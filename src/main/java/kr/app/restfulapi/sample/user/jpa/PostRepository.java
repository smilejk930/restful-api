package kr.app.restfulapi.sample.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.app.restfulapi.sample.user.post.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
