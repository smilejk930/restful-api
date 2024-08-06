package kr.app.restfulapi.domain.common.file.dto;

import org.apache.commons.lang3.ObjectUtils;
import kr.app.restfulapi.domain.common.file.entity.BaseFileEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileReqstDto<T extends BaseFileEntity> {

  private String fileGroupNm;
  private String rfrncTsid;
  private String fileClsfNm;

  @SuppressWarnings("unchecked")
  public T toEntity(T fileEntity) {
    return (T) fileEntity.toBuilder().fileGroupNm(fileGroupNm).rfrncTsid(rfrncTsid).fileClsfNm(fileClsfNm).build();
  }

  public boolean isAnyNullOfRequiredlField() {
    return ObjectUtils.anyNull(fileGroupNm, rfrncTsid);
  }
}
