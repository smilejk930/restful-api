package kr.app.restfulapi.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // 1) All requests should be authenticated; 모든 리퀘스트가 인증 받게 됨(HTTP ERROR 403)
    http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

    // 2) If a request is not authenticated, a web page is shown; 웹브라우저에서 리퀘스트 요청 시 팝업으로 계정정보를 확인
    http.httpBasic(withDefaults());

    // 3) CSRF -> POST, PUT; CSRF 해제 -> POST, PUT 가능
    http.csrf(csrf -> csrf.disable());

    return http.build();
  }
}
