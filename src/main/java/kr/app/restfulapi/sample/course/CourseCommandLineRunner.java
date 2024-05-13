package kr.app.restfulapi.sample.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import kr.app.restfulapi.sample.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
// 어플리케이션이 시작할때 실행되게 하려면 implements CommandLineRunner
public class CourseCommandLineRunner implements CommandLineRunner {

  // @Autowired
  // private CourseJdbcRepository repository;

  // @Autowired
  // private CourseJpaRepository repository;

  @Autowired
  private CourseSpringDataJpaRepository repository;

  @Override
  public void run(String... args) throws Exception {
    repository.save(new Course(1, "Learn AWS SpringDataJPA", "smilejk930"));
    repository.save(new Course(2, "Learn Azure", "smilejk"));
    repository.save(new Course(3, "Learn DevOps", "kjk"));

    repository.deleteById(3l);

    System.out.println(repository.findById(1l));
    System.out.println(repository.findById(2l));
  }

}
