package kr.app.restfulapi.sample.course;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Course {

  @Id
  private long id;

  // @Column(name="name")
  private String name;

  // @Column(name="author")
  private String author;
}
