package kr.app.restfulapi.sample.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
// 어플리케이션이 시작할때 실행되게 하려면 implements CommandLineRunner
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

  @Autowired
  private CourseJdbcRepository repository;

  @Override
  public void run(String... args) throws Exception {
    repository.insert();
  }

}
