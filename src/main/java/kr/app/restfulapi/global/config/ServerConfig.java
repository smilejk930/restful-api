package kr.app.restfulapi.global.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "server-type")
@Getter
@Setter
public class ServerConfig {
  private String name;
}
