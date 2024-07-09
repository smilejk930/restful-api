package kr.app.restfulapi.domain.sample.edu.todo;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Database (MySQL) 
//Static List of todos => Database (H2, MySQL)

//JPA
// Bean -> Database Table

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Todo {

  @Id
  @GeneratedValue
  private Integer id;

  private String username;

  @Size(min = 10, message = "Enter atleast 10 characters")
  private String description;
  private LocalDate targetDate;
  private boolean done;
}
