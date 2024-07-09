package kr.app.restfulapi.domain.common.file.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.domain.common.file.entity.FileData;

@Repository
public interface FileRepository extends JpaRepository<FileData, String>, FileRepositoryCustom {
  Optional<FileData> findByFileIdAndDeleteAt(String fileId, String deleteAt);
}
