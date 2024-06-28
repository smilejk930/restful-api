package kr.app.restfulapi.uga.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.app.restfulapi.uga.post.entity.Post;
import kr.app.restfulapi.uga.user.entity.User;

public record PostDto(
    String postId,
    @NotBlank @Size(min = 3, max = 200, message = "제목은 {min}자 이상, {max}자 이하로 입력해주세요.") String sj,
    @NotBlank @Size(min = 1, max = 4000, message = "내용은 {min}자 이상, {max}자 이하로 입력해주세요.") String cn) {

  static public PostDto toDto(Post post) {
    return new PostDto(post.getPostId(), post.getSj(), post.getCn());
  }

  public Post toEntity(User user) {
    return Post.builder().postId(postId).sj(sj).cn(cn).user(user).build();
  }
}
