package kr.app.restfulapi.global.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class JwtUtilsTest {

  @Autowired
  private JwtUtils jwtUtils;

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
    assertEquals("smilejk9301", jwtUtils.extractLoginId(generateToken()));
  }

  @Test
  void validateToken() {
    String token =
        "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1Z2EuZ28ua3IiLCJsb2dpbklkIjoic21pbGVqazkzMCIsImlhdCI6MTcyMDUwODk0MCwiZXhwIjoxNzIwNTEwNzQwLCJuYmYiOjE3MjA1MDg5NDB9.MRyg6aFL54o278WivfGc3-JT01EFaaeOxtUOHSn-J_Q";

    assertEquals(false, jwtUtils.validateToken(token, "smilejk930"));
  }

  String generateToken() {
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("loginId", "smilejk930");
    String token = jwtUtils.generateToken(claims);
    log.info(token);

    return token;
  }

}
