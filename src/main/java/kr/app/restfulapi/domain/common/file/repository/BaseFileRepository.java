package kr.app.restfulapi.domain.common.file.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import kr.app.restfulapi.global.entity.BaseFileEntity;

@NoRepositoryBean
public interface BaseFileRepository<T extends BaseFileEntity> extends JpaRepository<T, String>, BaseFileRepositoryCustom<T> {
  Optional<T> findByFileIdAndDelYn(String fileId, String delYn);
}
