package kr.app.restfulapi.domain.sample.edu.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.sample.edu.user.post.Post;

@Repository("samplePostRepository")
public interface PostRepository extends JpaRepository<Post, Integer> {

}
