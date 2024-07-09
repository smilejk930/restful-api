package kr.app.restfulapi.uga.file.repository;

import java.util.List;
import kr.app.restfulapi.uga.file.entity.FileData;

public interface FileRepositoryCustom {

  List<FileData> findAllWithCriteria(FileData fileData);
}
