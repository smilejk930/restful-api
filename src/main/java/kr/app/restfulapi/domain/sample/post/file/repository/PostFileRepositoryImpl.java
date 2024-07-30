package kr.app.restfulapi.domain.sample.post.file.repository;

import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.app.restfulapi.domain.common.file.repository.BaseFileRepositoryImpl;
import kr.app.restfulapi.domain.sample.post.file.entity.PostFile;
import kr.app.restfulapi.domain.sample.post.file.entity.QPostFile;

@Repository
public class PostFileRepositoryImpl extends BaseFileRepositoryImpl<PostFile> {

  public PostFileRepositoryImpl(JPAQueryFactory queryFactory) {
    super(queryFactory, QPostFile.postFile);
  }
}
