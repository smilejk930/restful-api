package kr.app.restfulapi.domain.common.file.repository;

import java.util.List;
import kr.app.restfulapi.domain.common.file.entity.FileData;

public interface FileRepositoryCustom {

  List<FileData> findAllWithCriteria(FileData fileData);
}
