package kr.app.restfulapi.uga.post.dto;

import kr.app.restfulapi.uga.post.entity.Post;
import kr.app.restfulapi.uga.user.entity.User;

public record PostDto(String postId, String sj, String cn) {

  static public PostDto toDto(Post post) {
    return new PostDto(post.getPostId(), post.getSj(), post.getCn());
  }

  public Post toEntity(User user) {
    return Post.builder().postId(postId).sj(sj).cn(cn).user(user).build();
  }
}
