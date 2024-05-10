package kr.app.restfulapi.sample.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * custom한 currency-service에 대해 properties를 설정한다.
 * currency-service.url
 * currency-service.username
 * currency-service.key
 */
@ConfigurationProperties(prefix = "currency-service")
@Getter
@Setter
@Component
public class CurrencyServiceConfiguration {
  private String url;
  private String username;
  private String key;
}
