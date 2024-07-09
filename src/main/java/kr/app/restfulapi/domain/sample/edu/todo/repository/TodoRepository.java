package kr.app.restfulapi.domain.sample.edu.todo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import kr.app.restfulapi.domain.sample.edu.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

  List<Todo> findByUsername(String username);

}
