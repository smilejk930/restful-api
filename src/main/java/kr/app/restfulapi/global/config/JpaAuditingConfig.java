package kr.app.restfulapi.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import kr.app.restfulapi.global.auditor.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing // (auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
  @Bean
  public AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }
}
