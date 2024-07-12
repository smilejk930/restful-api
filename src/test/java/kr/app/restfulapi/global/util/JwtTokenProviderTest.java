package kr.app.restfulapi.global.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import kr.app.restfulapi.domain.common.user.util.UserPrincipal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @SpringBootTest
class JwtTokenProviderTest {

  @InjectMocks
  private JwtTokenProvider tokenProvider;

  @Mock
  private Authentication authentication;

  @Mock
  private UserPrincipal userPrincipal;

  @Test
  void generateSecretKey() {
    SecretKey key = Jwts.SIG.HS256.key().build();
    String secretString = Encoders.BASE64.encode(key.getEncoded());
    log.info(secretString);
    SecretKey key2 = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

    assert key.equals(key2);
  }

  @Test
  void extractAllClaims() {
    assertEquals("smilejk9301", tokenProvider.getLoginIdFromJWT(generateToken2()));
  }

  @Test
  void validateToken() {
    String token =
        "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1Z2EuZ28ua3IiLCJsb2dpbklkIjoic21pbGVqazkzMCIsImlhdCI6MTcyMDUwODk0MCwiZXhwIjoxNzIwNTEwNzQwLCJuYmYiOjE3MjA1MDg5NDB9.MRyg6aFL54o278WivfGc3-JT01EFaaeOxtUOHSn-J_Q";

    assertEquals(false, tokenProvider.validateToken(token, "smilejk930"));
  }

  private String secretKey = "yourTestSecretKeyHereThatIsAtLeast32BytesLong";
  private String siteDomain = "https://yourdomain.com";
  private Long expirationMinutes = 30L;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    ReflectionTestUtils.setField(tokenProvider, "secretString", secretKey);
    ReflectionTestUtils.setField(tokenProvider, "siteDomain", siteDomain);
    ReflectionTestUtils.setField(tokenProvider, "expirationMinutes", expirationMinutes);
  }

  @Test
  String generateToken_ShouldCreateValidToken() {
    // Arrange
    String loginId = "testUser";
    when(authentication.getPrincipal()).thenReturn(userPrincipal);
    when(userPrincipal.getLoginId()).thenReturn(loginId);

    // Act
    String token = tokenProvider.generateToken(authentication);

    // Assert
    assertNotNull(token);

    // Decode and verify the token
    // Claims claims = Jwts.parser().verifyWith(tokenProvider.getSecretKey()).build().parseSignedClaims(token).getPayload();
    /*
    assertEquals(siteDomain, claims.getIssuer());
    assertEquals(loginId, claims.get("loginId"));
    assertNotNull(claims.getIssuedAt());
    assertNotNull(claims.getExpiration());
    */
    // Check if expiration is set correctly
    /*
    Instant expectedExpiration = claims.getIssuedAt().toInstant().plusSeconds(expirationMinutes * 60);
    assertEquals(expectedExpiration, claims.getExpiration().toInstant());
    
    // Verify that the token is not expired
    assertTrue(claims.getExpiration().after(new Date()));
    */
    return token;
  }

  @Test
  String generateToken2() {
    Map<String, Object> claims = new HashMap<>();

    claims.put("loginId2", userPrincipal.getLoginId());

    String jws = Jwts.builder()
        .issuer(siteDomain)
        .claims(claims)
        .issuedAt(Timestamp.valueOf(LocalDateTime.now()))
        .expiration(Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
        .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
        .compact();

    return jws;
  }
}
