package kr.app.restfulapi.domain.sample.edu.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * API의 버전관리에 대한 컨트롤러
 * - URI Versioning - Twitter
 *  - http://localhost:9090/v1/person
 *  - http://localhost:9090/v2/person
 * - Request Parameter versioning - Amazon
 *  - http://localhost:9090/person?version=1
 *  - http://localhost:9090/person?version=2
 * - (Custom) headers versioning - Microsoft
 *  - SAME-URL headers=[X-API-VERSION=1]
 *  - SAME-URL headers=[X-API-VERSION=2]
 * - Media type versioning (a.k.a “content negotiation” or “accept header”) - GitHub
 *  - SAME-URL produces=application/vnd.company.app-v1+json
 *  - SAME-URL produces=application/vnd.company.app-v2+json
 */
@RestController
public class VersioningPersonController {

  @GetMapping("/v1/person")
  public PersonV1 getFirstVersionOfPerson() {
    return new PersonV1("smilejk930");
  }

  @GetMapping("/v2/person")
  public PersonV2 getSecondVersionOfPerson() {
    return new PersonV2(new Name("smile", "jk930"));
  }

  @GetMapping(path = "/person", params = "version=1")
  public PersonV1 getFirstVersionOfPersonRequestParameter() {
    return new PersonV1("smilejk930");
  }

  @GetMapping(path = "/person", params = "version=2")
  public PersonV2 getSecondVersionOfPersonRequestParameter() {
    return new PersonV2(new Name("smile", "jk930"));
  }

  // X-API-VERSION: Header로 전송된 값을 얻으려면 headers 사용
  @GetMapping(path = "/person", headers = "X-API-VERSION=1")
  public PersonV1 getFirstVersionOfPersonHeader() {
    return new PersonV1("smilejk930");
  }

  @GetMapping(path = "/person", headers = "X-API-VERSION=2")
  public PersonV2 getSecondVersionOfPersonHeader() {
    return new PersonV2(new Name("smile", "jk930"));
  }

  // Accept: Header로 전송된 값을 얻으려면 produces 사용
  @GetMapping(path = "/person", produces = "application/vnd.company.app-v1+json")
  public PersonV1 getFirstVersionOfPersonAcceptHeader() {
    return new PersonV1("smilejk930");
  }

  @GetMapping(path = "/person", produces = "application/vnd.company.app-v2+json")
  public PersonV2 getSecondVersionOfPersonAcceptHeader() {
    return new PersonV2(new Name("smile", "jk930"));
  }
}
