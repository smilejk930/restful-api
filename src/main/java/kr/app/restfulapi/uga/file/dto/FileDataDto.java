package kr.app.restfulapi.uga.file.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.app.restfulapi.uga.file.entity.FileData;

public record FileDataDto(String fileId, String finm, Long filesiz, String fileEventn, @JsonIgnore String flpth) {

  public static FileDataDto toDto(FileData fileData) {
    return new FileDataDto(fileData.getFileId(), fileData.getFinm(), fileData.getFilesiz(), fileData.getFileEventn(), fileData.getFlpth());
  }
}
