package kr.app.restfulapi.sample.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.sample.user.post.Post;

@Repository("samplePostRepository")
public interface PostRepository extends JpaRepository<Post, Integer> {

}
