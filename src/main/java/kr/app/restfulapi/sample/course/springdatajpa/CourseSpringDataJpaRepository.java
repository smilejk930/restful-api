package kr.app.restfulapi.sample.course.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.app.restfulapi.sample.course.Course;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {

}
