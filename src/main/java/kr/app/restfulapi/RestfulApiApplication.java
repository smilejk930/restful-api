package kr.app.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestfulApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestfulApiApplication.class, args);
  }

  // Cross Origin Requests
  // Allow all requests only form http://localhost:3000/
  @Bean
  public WebMvcConfigurer corsConfigure() {
    return new WebMvcConfigurer() {
      @Override
      // 특정한 패턴에 대해 Cross Origin Requests 처리
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:3000");
      }
    };
  }


}
