package kr.app.restfulapi.uga.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.uga.file.entity.FileData;

@Repository
public interface FileRepository extends JpaRepository<FileData, String> {
}
