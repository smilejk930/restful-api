package kr.app.restfulapi.domain.sample.post.repository;

import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.file.repository.BaseFileRepository;
import kr.app.restfulapi.domain.sample.post.entity.PostFile;

@Repository
public interface PostFileRepository extends BaseFileRepository<PostFile> {
  // PostFile 전용 메서드를 여기에 정의할 수 있습니다.
}
