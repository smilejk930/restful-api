package kr.app.restfulapi.uga.post.dto;

import kr.app.restfulapi.uga.post.entity.Post;
import kr.app.restfulapi.uga.user.entity.User;

public record PostDto(Integer id, String title, String content) {

  static public PostDto toDto(Post post) {
    return new PostDto(post.getId(), post.getTitle(), post.getContent());
  }

  public Post toEntity(User user) {
    return Post.builder().id(id).title(title).content(content).user(user).build();
  }
}
