package kr.app.restfulapi.sample.user;

//메소드에 대해 정적 임포트를 수행
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import kr.app.restfulapi.sample.user.jpa.PostRepository;
import kr.app.restfulapi.sample.user.jpa.UserRepository;
import kr.app.restfulapi.sample.user.post.Post;

@RestController
public class UserJpaResource {

  private final UserRepository userRepository;
  private final PostRepository postRepository;

  public UserJpaResource(@Qualifier("sampleUserRepository") UserRepository userRepository,
      @Qualifier("samplePostRepository") PostRepository postRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  // 모든 사용자를 조회하는 메소드
  @GetMapping("/jpa/users")
  public List<User> retrieveAllUsers() {
    return userRepository.findAll();
  }

  // 사용자를 조회하는 메소드
  @GetMapping("/jpa/users/{id}")
  public EntityModel<User> retrieveOneUser(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    /*
     * EntityModel은 Spring HATEOAS에서 제공하는 클래스로, RESTful API에서 리소스를 표현하고 관련 링크를 추가하는데 사용됩니다.
     */
    EntityModel<User> entityModel = EntityModel.of(user.get());
    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers()); // 컨트롤러 메소드를 가리키는 링크 생성
    entityModel.add(link.withRel("all-users"));// "all-users" 관계를 가진 링크를 entityModel에 추가합니다.

    return entityModel;
  }

  // 사용자를 생성하는 메소드
  @PostMapping("/jpa/users")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

    User savedUser = userRepository.save(user);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest() // 현재 요청에 해당하는 URL을 반환하고
        .path("/{id}") // 생성된 사용자의 id를 반환
        .buildAndExpand(savedUser.getId())
        .toUri();
    // ResponseEntity를 사용하여 생성된 사용자의 URI를 포함한 응답을 반환합니다. 상태 코드는 201 Created입니다.
    return ResponseEntity.created(location).build();
  }

  // 사용자를 삭제하는 메소드
  @DeleteMapping("/jpa/users/{id}")
  public void deleteUser(@PathVariable int id) {
    userRepository.deleteById(id);
  }

  // 사용자의 모든 게시물을 조회하는 메소드
  @GetMapping("/jpa/users/{id}/posts")
  public List<Post> retrievePostsForUser(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    return user.get().getPosts();
  }

  // 사용자의 게시물을 조회하는 메소드
  @GetMapping("/jpa/users/{id}/posts/{postId}")
  public EntityModel<Post> retrieveOnePostForUser(@PathVariable int id, @PathVariable int postId) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    Optional<Post> post = postRepository.findById(postId);
    if (post.isEmpty() || post.get().getUser().getId() != user.get().getId()) {
      throw new UserNotFoundException(String.format("POST[%s] not found", postId));
    }

    /*
    * EntityModel은 Spring HATEOAS에서 제공하는 클래스로, RESTful API에서 리소스를 표현하고 관련 링크를 추가하는데 사용됩니다.
    */
    EntityModel<Post> entityModel = EntityModel.of(post.get());
    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePostsForUser(id)); // 컨트롤러 메소드를 가리키는 링크 생성
    entityModel.add(link.withRel("all-posts"));// "all-posts" 관계를 가진 링크를 entityModel에 추가합니다.

    return entityModel;
  }

  // 사용자의 게시물을 생성하는 메소드
  @PostMapping("/jpa/users/{id}/posts")
  public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    post.setUser(user.get());

    Post savedPost = postRepository.save(post);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest() // 현재 요청에 해당하는 URL을 반환하고
        .path("/{id}") // 생성된 게시물의 id를 반환
        .buildAndExpand(savedPost.getId())
        .toUri();
    // ResponseEntity를 사용하여 생성된 게시물의 URI를 포함한 응답을 반환합니다. 상태 코드는 201 Created입니다.
    return ResponseEntity.created(location).build();
  }

  // 사용자의 게시물을 삭제하는 메소드
  @DeleteMapping("/jpa/users/{id}/posts/{postId}")
  public void deletePostForUser(@PathVariable int id, @PathVariable int postId) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    Optional<Post> post = postRepository.findById(postId);
    if (post.isEmpty() || post.get().getUser().getId() != user.get().getId()) {
      throw new UserNotFoundException(String.format("POST[%s] not found", postId));
    }

    postRepository.deleteById(postId);
  }
}
