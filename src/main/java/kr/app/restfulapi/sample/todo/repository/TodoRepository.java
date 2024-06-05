package kr.app.restfulapi.sample.todo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import kr.app.restfulapi.sample.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

  List<Todo> findByUsername(String username);

}
