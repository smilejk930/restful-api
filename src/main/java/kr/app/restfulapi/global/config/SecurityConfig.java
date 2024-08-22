package kr.app.restfulapi.global.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import kr.app.restfulapi.domain.common.user.gnrl.service.CustomUserDetailsService;
import kr.app.restfulapi.global.filter.DynamicAuthorizationFilter;
import kr.app.restfulapi.global.filter.GlobalExceptionTranslationFilter;
import kr.app.restfulapi.global.filter.JwtAuthenticationFilter;
import kr.app.restfulapi.global.response.error.exception.SecurityExceptionHandler;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final SecurityExceptionHandler securityExceptionHandler;
  private final GlobalExceptionTranslationFilter globalExceptionTranslationFilter;
  private final CustomUserDetailsService customUserDetailsService;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final DynamicAuthorizationFilter dynamicAuthorizationFilter;

  @Value("${cors.allowed-origins}")
  private String allowedOrigins;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterAfter(dynamicAuthorizationFilter, JwtAuthenticationFilter.class)
        .addFilterBefore(globalExceptionTranslationFilter, ExceptionTranslationFilter.class)// 필터에서의 전역 exception 처리
        .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(securityExceptionHandler) // AuthenticationEntryPoint는 인증되지
                                                                                                                     // 않은 사용자가 보안된 리소스에 접근하려 할 때 호출
            .accessDeniedHandler(securityExceptionHandler)) // AccessDeniedHandler는 인증된 사용자가 권한이 없는 리소스에 접근하려 할 때 호출
    ;

    return http.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(customUserDetailsService);// UserDetailsService 설정
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public FilterRegistrationBean<GlobalExceptionTranslationFilter> globalExceptionTranslationFilterRegistration(
      GlobalExceptionTranslationFilter filter) {
    FilterRegistrationBean<GlobalExceptionTranslationFilter> registration = new FilterRegistrationBean<>(filter);
    registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return registration;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * 프레임 옵션 대신 CORS를 구성하여, 특정 도메인에서의 API 접근을 허용
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
