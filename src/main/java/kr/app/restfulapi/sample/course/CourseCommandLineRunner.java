package kr.app.restfulapi.sample.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import kr.app.restfulapi.sample.course.jpa.CourseJpaRepository;

@Component
// 어플리케이션이 시작할때 실행되게 하려면 implements CommandLineRunner
public class CourseCommandLineRunner implements CommandLineRunner {

  // @Autowired
  // private CourseJdbcRepository repository;

  @Autowired
  private CourseJpaRepository repository;

  @Override
  public void run(String... args) throws Exception {
    repository.insert(new Course(1, "Learn AWS", "smilejk930"));
    repository.insert(new Course(2, "Learn Azure", "smilejk"));
    repository.insert(new Course(3, "Learn DevOps", "kjk"));

    repository.deleteById(3);

    System.out.println(repository.findById(1));
    System.out.println(repository.findById(2));
  }

}
