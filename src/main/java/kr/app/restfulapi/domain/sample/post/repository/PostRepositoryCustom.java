package kr.app.restfulapi.domain.sample.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import kr.app.restfulapi.domain.sample.post.entity.Post;

public interface PostRepositoryCustom {

  Page<Post> findAllWithCriteria(Post post, Pageable pageable);
}
