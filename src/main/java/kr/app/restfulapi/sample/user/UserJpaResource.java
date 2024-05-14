package kr.app.restfulapi.sample.user;

//메소드에 대해 정적 임포트를 수행
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Optional;
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
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import kr.app.restfulapi.sample.user.jpa.UserRepository;
import kr.app.restfulapi.sample.user.post.Post;

@RestController
public class UserJpaResource {

  @Resource
  private UserRepository repository;

  // 모든 사용자를 검색하는 메소드
  @GetMapping("/jpa/users")
  public List<User> retrieveAllUsers() {
    return repository.findAll();
  }

  // 특정 사용자를 검색하는 메소드
  @GetMapping("/jpa/users/{id}")
  public EntityModel<User> retrieveOneUsers(@PathVariable int id) {
    Optional<User> user = repository.findById(id);
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

    User savedUser = repository.save(user);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest() // 현재 요청에 해당하는 URL을 반환하고
        .path("/{id}") // 생성된 사용자의 id를 반환
        .buildAndExpand(savedUser.getId()).toUri();
    // ResponseEntity를 사용하여 생성된 사용자의 URI를 포함한 응답을 반환합니다. 상태 코드는 201 Created입니다.
    return ResponseEntity.created(location).build();
  }

  // 사용자를 삭제하는 메소드
  @DeleteMapping("/jpa/users/{id}")
  public void deleteUser(@PathVariable int id) {
    repository.deleteById(id);
  }

  // 사용자의 모든 게시물을 조회하는 메소드
  @GetMapping("/jpa/users/{id}/posts")
  public List<Post> retrievePostsForUser(@PathVariable int id) {
    Optional<User> user = repository.findById(id);
    if (user.isEmpty()) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    return user.get().getPosts();
  }
}
