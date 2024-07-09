package kr.app.restfulapi.uga.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import kr.app.restfulapi.uga.post.entity.Post;

public interface PostRepositoryCustom {

  Page<Post> findAllWithCriteria(Post post, Pageable pageable);
}
