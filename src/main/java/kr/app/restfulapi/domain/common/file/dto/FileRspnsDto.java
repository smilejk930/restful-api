package kr.app.restfulapi.domain.common.file.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.app.restfulapi.domain.common.file.entity.BaseFileEntity;

public record FileRspnsDto(
    String fileTsid,
    String fileNm,
    String fileClsfNm,
    Long fileSeq,
    String fileExtnNm,
    Long fileSize,
    String fileSyncCd,
    @JsonIgnore String strgFileNm,
    @JsonIgnore String fileStreCours) {

  public static <T extends BaseFileEntity> FileRspnsDto toDto(T fileEntity) {
    return new FileRspnsDto(
        fileEntity.getFileTsid(),
        fileEntity.getFileNm(),
        fileEntity.getFileClsfNm(),
        fileEntity.getFileSeq(),
        fileEntity.getFileExtnNm(),
        fileEntity.getFileSize(),
        fileEntity.getFileSyncCd(),
        fileEntity.getStrgFileNm(),
        fileEntity.getFileStreCours());
  }
}
