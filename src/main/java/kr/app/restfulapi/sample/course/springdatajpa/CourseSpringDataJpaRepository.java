package kr.app.restfulapi.sample.course.springdatajpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import kr.app.restfulapi.sample.course.Course;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {

  List<Course> findByAuthor(String author);

  List<Course> findByName(String name);

}
