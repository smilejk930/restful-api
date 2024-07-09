package kr.app.restfulapi.domain.common.file.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.app.restfulapi.domain.common.file.entity.FileData;

public record FileDataDto(
    String fileId,
    String fileNm,
    String fileSectValue,
    Long fileSn,
    String fileExtsnNm,
    Long fileSize,
    String fileSynchrnCode,
    @JsonIgnore String fileStreNm,
    @JsonIgnore String fileStreCours) {

  public static FileDataDto toDto(FileData fileData) {
    return new FileDataDto(
        fileData.getFileId(),
        fileData.getFileNm(),
        fileData.getFileSectValue(),
        fileData.getFileSn(),
        fileData.getFileExtsnNm(),
        fileData.getFileSize(),
        fileData.getFileSynchrnCode(),
        fileData.getFileStreNm(),
        fileData.getFileStreCours());
  }
}
