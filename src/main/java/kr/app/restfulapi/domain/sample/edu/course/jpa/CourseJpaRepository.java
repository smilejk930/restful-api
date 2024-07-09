package kr.app.restfulapi.domain.sample.edu.course.jpa;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kr.app.restfulapi.domain.sample.edu.course.Course;

@Repository
@Transactional
public class CourseJpaRepository {

  // @Autowired
  @PersistenceContext // 더 구체적인 어노테이션
  private EntityManager entityManager;

  public void insert(Course course) {
    entityManager.merge(course);
  }

  public Course findById(long id) {
    return entityManager.find(Course.class, id);
  }

  public void deleteById(long id) {
    Course course = entityManager.find(Course.class, id);
    entityManager.remove(course);
  }
}
