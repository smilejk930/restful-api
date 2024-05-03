package kr.app.restfulapi.sample.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

  private static List<User> users = new ArrayList<>();
  private static int usersCount = 0;

  static {
    users.add(new User(++usersCount, "Kenneth", LocalDate.now().minusYears(30)));
    users.add(new User(++usersCount, "Alice", LocalDate.now().minusYears(20)));
    users.add(new User(++usersCount, "Elena", LocalDate.now().minusYears(10)));
  }

  public List<User> findAll() {
    return users;
  }

  public User save(User user) {
    user.setId(++usersCount);
    users.add(user);
    return user;
  }

  public User findOne(int id) {
    // Predicate<? super User> predicate = user -> user.getId() == id;
    // 해당 ID를 가진 사용자를 찾아 반환하거나, 해당 ID에 해당하는 사용자가 없을 경우 null을 반환합니다.
    return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
  }
}
