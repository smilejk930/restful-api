package kr.app.restfulapi.global.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kr.app.restfulapi.global.response.error.ErrorStatus;
import kr.app.restfulapi.global.response.error.exception.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtUtils {

  @Value("${jwt.secret-key}")
  private String secretString;

  @Value("${jwt.expiration-minutes}")
  private Long expirationMinutes;

  @Value("${jwt.expiration-hours}")
  private Long expirationHours;

  @Value("${site.domain}")
  private String siteDomain;

  public String generateToken(Map<String, ?> claims) {

    return Jwts.builder()
        .issuer(siteDomain)
        .claims(claims)
        .issuedAt(Timestamp.valueOf(LocalDateTime.now()))
        .expiration(Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
        // .expiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))
        .notBefore(Timestamp.valueOf(LocalDateTime.now()))
        .signWith(getSecretKey())
        .compact();
  }

  public String extractLoginId(String token) {
    return extractClaim(token, claims -> claims.get("loginId", String.class));
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {

    Claims claims = null;

    try {
      claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
    } catch (JwtException ex) {
      log.error(ex.getMessage());
      throw new BusinessException(ErrorStatus.UNAUTHORIZED, ex.getMessage());
    }
    return claims;
  }

  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
  }

  // TODO jwt-해당 메소드가 필요한지 판단 필요
  public boolean validateToken(String token, String loginId) {
    return extractLoginId(token).equals(loginId) && !isTokenExpired(token);
  }

  // TODO jwt-해당 메소드가 필요한지 판단 필요(JwtException에서 유효기간 체크 해줌)
  private boolean isTokenExpired(String token) {
    return extractClaim(token, Claims::getExpiration).before(Timestamp.valueOf(LocalDateTime.now()));
  }
}
