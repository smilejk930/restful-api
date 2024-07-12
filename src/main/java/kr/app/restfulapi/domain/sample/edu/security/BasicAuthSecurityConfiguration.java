package kr.app.restfulapi.domain.sample.edu.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthSecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // 1: Response to preflight request dosen't pass access control check; 프리플라이트 요청에 대한 응답이 액세스 제어 체크를 통과하지 못함(OPTIONS 요청 허용)
    // 2: basic auth; 로그인 시점에 기본 인증 URL 호출하여 토큰이 유효한지 확인
    return http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**")
        .permitAll() // 모든 URL에 대한 OPTIONS 요청 허용
        .anyRequest()
        .authenticated()) // All requests should be authenticated; 모든 리퀘스트가 인증 받게 됨(HTTP ERROR 403)
        .httpBasic(withDefaults()) // If a request is not authenticated, a web page is shown; 웹브라우저에서 리퀘스트 요청 시 팝업으로 계정정보를 확인
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 스프링시큐리티가 세션을 생성하지도 않고 기존 것을 사용하지도 않음
                                                                                                      // (JWT 같은토큰방식을 쓸때 사용하는 설정)
        .csrf(csrf -> csrf.disable()) // CSRF -> POST, PUT; CSRF 해제 -> POST, PUT 가능
        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // Request가 동일한 origin에서 오는 경우 해당 애플리케이션에 프레임을 허용
        .build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("smilejk")
        // .password("{noop}password") // {noop}: 인코딩을 하지 않음
        // .password("password").passwordEncoder(passwordStr -> passwordEncoder().encode(passwordStr))
        .password("{bcrypt}" + passwordEncoder().encode("password")) // BCrypt 해시 함수로 암호화
        .authorities("read")
        .roles("USER")
        .build();

    UserDetails admin = User.withUsername("admin")
        // .password("{noop}password")
        // .password("password").passwordEncoder(passwordStr -> passwordEncoder().encode(passwordStr))
        .password("{bcrypt}" + passwordEncoder().encode("password")) // BCrypt 해시 함수로 암호화
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user, admin);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
