package kr.app.restfulapi.domain.sample.edu.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import kr.app.restfulapi.domain.sample.edu.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
// 어플리케이션이 시작할때 실행되게 하려면 implements CommandLineRunner
public class CourseCommandLineRunner implements CommandLineRunner {

  // @Autowired
  // private CourseJdbcRepository repository;

  // @Autowired
  // private CourseJpaRepository repository;

  private final CourseSpringDataJpaRepository repository;

  public CourseCommandLineRunner(CourseSpringDataJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    repository.save(new Course(1, "Learn AWS SpringDataJPA", "smilejk930"));
    repository.save(new Course(2, "Learn Azure", "smilejk"));
    repository.save(new Course(3, "Learn DevOps", "kjk"));

    repository.deleteById(3l);

    System.out.println(repository.findById(1l));
    System.out.println(repository.findById(2l));

    System.out.println(repository.findAll());
    System.out.println(repository.count());

    System.out.println(repository.findByAuthor("smilejk"));
    System.out.println(repository.findByAuthor(""));

    System.out.println(repository.findByName("Learn AWS SpringDataJPA"));
    System.out.println(repository.findByName(""));
  }

}
