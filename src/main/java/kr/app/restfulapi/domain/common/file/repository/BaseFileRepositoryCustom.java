package kr.app.restfulapi.domain.common.file.repository;

import java.util.List;
import kr.app.restfulapi.domain.common.file.entity.BaseFileEntity;

public interface BaseFileRepositoryCustom<T extends BaseFileEntity> {
  List<T> findAllWithCriteria(T fileEntity);
}
