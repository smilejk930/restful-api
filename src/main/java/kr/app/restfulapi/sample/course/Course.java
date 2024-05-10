package kr.app.restfulapi.sample.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Course {

  public Course() {}

  private long id;
  private String name;
  private String author;
}
