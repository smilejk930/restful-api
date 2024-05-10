package kr.app.restfulapi.sample.course;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Course {

  private long id;
  private String name;
  private String author;
}
