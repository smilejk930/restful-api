package kr.app.restfulapi.global.util;

import org.junit.jupiter.api.Test;
import io.hypersistence.tsid.TSID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class TsidGenerationToStringTest {

  @Test
  void test() {
    for (int i = 0; i < 20; i++)
      log.info(TSID.fast().toString());
  }

}
