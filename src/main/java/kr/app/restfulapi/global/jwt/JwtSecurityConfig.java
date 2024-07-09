package kr.app.restfulapi.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    // https://github.com/spring-projects/spring-security/issues/1231
    // https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.sql.h2-web-console.spring-security
    return httpSecurity
        // .authorizeHttpRequests(auth -> auth.requestMatchers("/authenticate").permitAll()
        // .requestMatchers(PathRequest.toH2Console()).permitAll() //(사용X) h2-console is a servlet and NOT recommended for a production
        // .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // (사용X) Deprecated in SB 3.1.x
        // .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())) // Starting from SB 3.1.x using Lambda DSL
        .httpBasic(Customizer.withDefaults())
        // .headers(header -> { // (사용X) Deprecated in SB 3.1.x
        // header.frameOptions().sameOrigin(); // (사용X)
        // })
        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // Starting from SB 3.1.x using Lambda DSL
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
    var authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);

    return new ProviderManager(authenticationProvider);
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

  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    JWKSet jwkSet = new JWKSet(rsaKey());

    return (((jwkSelector, securityContext) -> jwkSelector.select(jwkSet)));
  }

  @Bean
  JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
    return new NimbusJwtEncoder(jwkSource);
  }

  @Bean
  JwtDecoder jwtDecoder() throws JOSEException {
    return NimbusJwtDecoder.withPublicKey(rsaKey().toRSAPublicKey()).build();
  }

  @Bean
  public RSAKey rsaKey() {
    KeyPair keyPair = keyPair();

    return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).privateKey((RSAPrivateKey) keyPair.getPrivate())
        .keyID(UUID.randomUUID().toString())
        .build();
  }

  @Bean
  public KeyPair keyPair() {
    try {
      var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      return keyPairGenerator.generateKeyPair();
    } catch (Exception e) {
      throw new IllegalStateException("Unable to generate an RSA Key Pair", e);
    }
  }

}


