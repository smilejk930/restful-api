package kr.app.restfulapi.domain.common.file.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.app.restfulapi.domain.common.file.entity.BaseFileEntity;

public record FileRspnsDto(
    String fileId,
    String fileNm,
    String fileSectValue,
    Long fileSn,
    String fileExtsnNm,
    Long fileSize,
    String fileSynchrnCode,
    @JsonIgnore String fileStreNm,
    @JsonIgnore String fileStreCours) {

  public static <T extends BaseFileEntity> FileRspnsDto toDto(T fileEntity) {
    return new FileRspnsDto(
        fileEntity.getFileId(),
        fileEntity.getFileNm(),
        fileEntity.getFileSectValue(),
        fileEntity.getFileSn(),
        fileEntity.getFileExtsnNm(),
        fileEntity.getFileSize(),
        fileEntity.getFileSynchrnCode(),
        fileEntity.getFileStreNm(),
        fileEntity.getFileStreCours());
  }
}
