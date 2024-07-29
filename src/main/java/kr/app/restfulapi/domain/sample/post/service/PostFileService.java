package kr.app.restfulapi.domain.sample.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.app.restfulapi.domain.common.file.service.BaseFileService;
import kr.app.restfulapi.domain.sample.post.entity.PostFile;
import kr.app.restfulapi.domain.sample.post.repository.PostFileRepository;

@Service
public class PostFileService extends BaseFileService<PostFile, PostFileRepository> {

  private final PostFileRepository postFileRepository;

  @Autowired
  public PostFileService(PostFileRepository postFileRepository) {
    super(postFileRepository);
    this.postFileRepository = postFileRepository;
  }
}
