package kr.app.restfulapi.sample.user;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserLogin {

  @GetMapping("/login")
  public String login(@RequestParam(required = false) String name,
      @RequestParam(required = false) String password, HttpSession session) {
    // name과 password가 모두 null이 아니고, name이 "admin"이고 password가 "password"인 경우
    if (Objects.nonNull(name) && Objects.nonNull(password) && name.equals("admin")
        && password.equals("password")) {
      session.setAttribute("user", name); // 세션에 사용자 정보 저장
      return "User Logged In"; // 사용자가 로그인 성공
    } else {
      return "Login Failed"; // 로그인 실패
    }
  }

  /*
   * @PostMapping("/login")
   * public ResponseEntity<String> login(@RequestParam(required = false) String
   * name,
   * 
   * @RequestParam(required = false) String password) {
   * // 유효성 검증을 수행합니다.
   * if (name == null || name.trim().isEmpty() || password == null ||
   * password.trim().isEmpty()) {
   * return ResponseEntity.badRequest().body("이름과 비밀번호를 모두 입력해주세요.");
   * }
   * 
   * // 사용자를 조회합니다.
   * User user = userRepository.findByName(name);
   * 
   * if (user == null) {
   * return ResponseEntity.badRequest().body("존재하지 않는 사용자입니다.");
   * }
   * 
   * // 비밀번호가 일치하는지 확인합니다.
   * if (!user.getPassword().equals(password)) {
   * return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
   * }
   * 
   * // 사용자를 인증합니다.
   * Authentication authentication = new UsernamePasswordAuthenticationToken(user,
   * null);
   * SecurityContextHolder.getContext().setAuthentication(authentication);
   * 
   * return ResponseEntity.ok("로그인에 성공했습니다.");
   * }
   */
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    // Check if the user is already logged in
    if (session.getAttribute("user") != null) {
      // If the user is logged in, clear their session attributes and redirect to the
      // login page
      session.invalidate();
      return "redirect:/login";
    } else {
      // If the user is not logged in, show an error message
      return "redirect:/login-error";
    }
  }
}
