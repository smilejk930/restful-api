package kr.app.restfulapi.domain.common.file.dto;

import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import kr.app.restfulapi.domain.common.file.entity.BaseFileEntity;
import kr.app.restfulapi.domain.common.file.util.FileGroupNmType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileReqstDto<T extends BaseFileEntity> {

  private FileGroupNmType fileGroupNm;
  private String rfrncTsid;
  private String fileClsfNm;
  private List<MultipartFile> files;

  /** 삭제파일식별번호들 */
  private List<String> delFileTsids;

  @SuppressWarnings("unchecked")
  public T toEntity(T fileEntity) {
    return (T) fileEntity.toBuilder().fileGroupNm(fileGroupNm).rfrncTsid(rfrncTsid).fileClsfNm(fileClsfNm).build();
  }

  public boolean isAnyNullOfRequiredlField() {
    return ObjectUtils.anyNull(fileGroupNm, rfrncTsid);
  }
}
