package kr.app.restfulapi.domain.sample.post.file.repository;

import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.file.repository.BaseFileRepository;
import kr.app.restfulapi.domain.sample.post.file.entity.PostFile;

@Repository
public interface PostFileRepository extends BaseFileRepository<PostFile> {
}
