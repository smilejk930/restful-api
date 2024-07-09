package kr.app.restfulapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import kr.app.restfulapi.auditor.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing // (auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
  @Bean
  public AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }
}
