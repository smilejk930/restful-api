package kr.app.restfulapi.global.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kr.app.restfulapi.domain.common.user.gnrl.util.UserPrincipal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtTokenProvider {

  @Value("${jwt.secret-key}")
  private String secretString;

  @Value("${jwt.expiration-minutes}")
  private Long expirationMinutes;

  @Value("${jwt.expiration-hours}")
  private Long expirationHours;

  @Value("${site.domain}")
  private String siteDomain;

  public String generateToken(Authentication authentication) {

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    Map<String, Object> claims = new HashMap<>();

    // TOKEN에 정보 주입
    claims.put("lgnId", userPrincipal.getLgnId());
    claims.put("userNm", userPrincipal.getUserNm());
    claims.put("authorities", userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());

    return Jwts.builder()
        .issuer(siteDomain)
        .claims(claims)
        .issuedAt(Timestamp.valueOf(LocalDateTime.now()))
        .expiration(Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
        // .expiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))
        // .notBefore(Timestamp.valueOf(LocalDateTime.now()))
        .signWith(getSecretKey())
        .compact();
  }

  public Optional<String> getLgnIdFromJWT(String token) {
    try {
      return Optional.of(extractClaim(token, claims -> claims.get("lgnId", String.class)));
    } catch (NullPointerException | JwtException ex) {
      log.error(ex.getMessage());
      return Optional.empty();
    }
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {

    return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
  }

  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
  }

  public boolean isValidateToken(String token) {
    try {
      extractAllClaims(token);
      return true;
      // } catch (SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
    } catch (JwtException ex) {
      log.error(ex.getMessage());
      return false;
    }
  }
}
