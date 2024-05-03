package kr.app.restfulapi.sample.user;


import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

  @Autowired
  private UserDaoService service;

  @GetMapping("/users")
  public List<User> retrieveAllUsers() {
    return service.findAll();
  }

  @GetMapping("/users/{id}")
  public User retrieveOneUsers(@PathVariable int id) {
    return service.findOne(id);
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {

    User savedUser = service.save(user);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest() // 현재 요청에 해당하는 URL을 반환하고
        .path("/{id}") // 생성된 사용자의 id를 반환
        .buildAndExpand(savedUser.getId()).toUri();
    // ResponseEntity를 사용하여 생성된 사용자의 URI를 포함한 응답을 반환합니다. 상태 코드는 201 Created입니다.
    return ResponseEntity.created(location).build();
  }
}
