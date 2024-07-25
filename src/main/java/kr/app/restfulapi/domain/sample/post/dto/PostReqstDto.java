package kr.app.restfulapi.domain.sample.post.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.domain.sample.post.entity.Post;
import kr.app.restfulapi.global.validation.ValidationGroups.FinalSubmit;

public record PostReqstDto(
    @NotBlank(message = "제목은 필수 입력값입니다.") @Size(min = 3, max = 200, message = "제목은 {min}자 이상, {max}자 이하로 입력해주세요.") String sj,
    @NotBlank(message = "내용은 필수 입력값입니다.", groups = {
        FinalSubmit.class})
    @Size(min = 1, max = 4000, message = "내용은 {min}자 이상, {max}자 이하로 입력해주세요.", groups = {FinalSubmit.class}) String cn,
    @Min(value = 1, message = "전문길이는 최소 1이어야 합니다.") Integer telgmLen){

  public Post toEntity() {
    return Post.builder().sj(sj).cn(cn).telgmLen(telgmLen).build();
  }
}
