package kr.app.restfulapi.domain.common.file.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import kr.app.restfulapi.global.entity.BaseFileEntity;

@Transactional
public abstract class BaseFileService<T extends BaseFileEntity, R extends JpaRepository<T, String>> {
  protected final R repository;

  protected BaseFileService(R repository) {
    this.repository = repository;
  }

  public List<T> findAll() {
    return repository.findAll();
  }

  public Optional<T> findById(String id) {
    return repository.findById(id);
  }

  public T save(T entity) {
    return repository.save(entity);
  }

  public void deleteById(String id) {
    repository.deleteById(id);
  }
}
