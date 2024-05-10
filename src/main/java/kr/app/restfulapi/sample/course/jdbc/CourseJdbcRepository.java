package kr.app.restfulapi.sample.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.app.restfulapi.sample.course.Course;

@Repository
public class CourseJdbcRepository {

  @Autowired
  private JdbcTemplate springjdbcTemplate;

  private static String INSERT_QUERY = """
          insert into course (id, name, author)
          values(?, ?, ?);
      """;

  private static String DELETE_QUERY = """
          delete from course
          where id = ?;
      """;

  public void insert(Course course) {
    springjdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
  }

  public void deleteById(long id) {
    springjdbcTemplate.update(DELETE_QUERY, id);
  }
}
