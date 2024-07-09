package kr.app.restfulapi.domain.sample.edu.user;

//메소드에 대해 정적 임포트를 수행
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
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

@RestController
public class UserResource {

  private final UserDaoService service;

  public UserResource(UserDaoService service) {
    this.service = service;
  }

  // 모든 사용자를 검색하는 메소드
  @GetMapping("/users")
  public List<User> retrieveAllUsers() {
    return service.findAll();
  }

  // 특정 사용자를 검색하는 메소드
  @GetMapping("/users/{id}")
  public EntityModel<User> retrieveOneUsers(@PathVariable int id) {
    User user = service.findOne(id);
    if (user == null) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    /*
     * EntityModel은 Spring HATEOAS에서 제공하는 클래스로, RESTful API에서 리소스를 표현하고 관련 링크를 추가하는데 사용됩니다.
     */
    EntityModel<User> entityModel = EntityModel.of(user);
    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers()); // 컨트롤러 메소드를 가리키는 링크 생성
    entityModel.add(link.withRel("all-users"));// "all-users" 관계를 가진 링크를 entityModel에 추가합니다.

    return entityModel;
  }

  // 사용자를 생성하는 메소드
  @PostMapping("/users")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

    User savedUser = service.save(user);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest() // 현재 요청에 해당하는 URL을 반환하고
        .path("/{id}") // 생성된 사용자의 id를 반환
        .buildAndExpand(savedUser.getId())
        .toUri();
    // ResponseEntity를 사용하여 생성된 사용자의 URI를 포함한 응답을 반환합니다. 상태 코드는 201 Created입니다.
    return ResponseEntity.created(location).build();
  }

  // 사용자를 삭제하는 메소드
  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable int id) {
    service.deleteById(id);
  }
}
