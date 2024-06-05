package kr.app.restfulapi.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SpringSecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // 1: Response to preflight request dosen't pass access control check; 프리플라이트 요청에 대한 응답이 액세스 제어 체크를 통과하지 못함(OPTIONS 요청 허용)
    // 2: basic auth; 로그인 시점에 기본 인증 URL 호출하여 토큰이 유효한지 확인
    return http
        .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 모든 URL에 대한 OPTIONS 요청 허용
            .anyRequest().authenticated()) // All requests should be authenticated; 모든 리퀘스트가 인증 받게 됨(HTTP ERROR 403)
        .httpBasic(withDefaults()) // If a request is not authenticated, a web page is shown; 웹브라우저에서 리퀘스트 요청 시 팝업으로 계정정보를 확인
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 스프링시큐리티가 세션을 생성하지도 않고 기존 것을 사용하지도 않음
                                                                                       // (JWT 같은토큰방식을 쓸때 사용하는 설정)
        .csrf(csrf -> csrf.disable())// CSRF -> POST, PUT; CSRF 해제 -> POST, PUT 가능
        .build();
  }
}
