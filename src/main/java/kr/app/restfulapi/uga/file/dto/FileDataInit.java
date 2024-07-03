package kr.app.restfulapi.uga.file.dto;

import org.apache.commons.lang3.ObjectUtils;
import kr.app.restfulapi.uga.file.entity.FileData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileDataInit {

  private String fileGroupNm;
  private String refrnId;
  private String fileSectValue;

  public FileData toEntity() {
    return FileData.builder().fileGroupNm(fileGroupNm).refrnId(refrnId).fileSectValue(fileSectValue).build();
  }

  public boolean isAnyNullOfRequiredlField() {
    return ObjectUtils.anyNull(fileGroupNm, refrnId);
  }
}
