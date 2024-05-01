package kr.app.restfulapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * custom한 currency-service에 대해 properties를 확인하는 클래스
 */
@RestController
public class CurrencyConfigurationController {

  @Autowired
  private CurrencyServiceConfiguration configuration;

  @GetMapping(value = "/currency-service")
  public CurrencyServiceConfiguration getCurrencyServiceConfiguration() {
    return configuration;
  }
}
