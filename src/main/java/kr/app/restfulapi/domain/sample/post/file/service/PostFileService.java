package kr.app.restfulapi.domain.sample.post.file.service;

import org.springframework.stereotype.Service;
import kr.app.restfulapi.domain.common.file.service.BaseFileService;
import kr.app.restfulapi.domain.sample.post.file.entity.PostFile;
import kr.app.restfulapi.domain.sample.post.file.repository.PostFileRepository;

@Service
public class PostFileService extends BaseFileService<PostFile> {

  public PostFileService(PostFileRepository postFileRepository) {
    super(PostFile.builder().build(), postFileRepository);
  }
}
