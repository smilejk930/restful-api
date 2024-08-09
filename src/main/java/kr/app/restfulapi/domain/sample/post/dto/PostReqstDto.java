package kr.app.restfulapi.domain.sample.post.dto;

import java.util.List;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.domain.common.file.dto.FileReqstDto;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.domain.sample.post.file.entity.PostFile;
import kr.app.restfulapi.global.validation.ValidationGroups.FinalSubmit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReqstDto {

  @NotBlank(message = "제목은 필수 입력값입니다.")
  @Size(min = 3, max = 200, message = "제목은 {min}자 이상, {max}자 이하로 입력해주세요.")
  private String ttl;

  @NotBlank(message = "내용은 필수 입력값입니다.", groups = {FinalSubmit.class})
  @Size(min = 1, max = 4000, message = "내용은 {min}자 이상, {max}자 이하로 입력해주세요.", groups = {FinalSubmit.class})
  private String cn;

  @Min(value = 1, message = "전문길이는 최소 {value}이어야 합니다.")
  private Integer telgmLen;

  private List<FileReqstDto<PostFile>> fileReqstDtos;

  public Post toEntity() {
    return Post.builder().ttl(ttl).cn(cn).telgmLen(telgmLen).build();
  }
}
