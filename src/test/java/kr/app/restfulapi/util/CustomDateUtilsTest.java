package kr.app.restfulapi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import kr.app.restfulapi.uga.common.util.CustomDateUtils;

class CustomDateUtilsTest {

  @Test
  void getCurrentDate() {
    assertEquals("20240702", CustomDateUtils.getCurrentDate());
  }

  @Test
  void getCurrentYear() {
    assertEquals("2024", CustomDateUtils.getCurrentYear());
  }

  @Test
  void getCurrentMonth() {
    assertEquals("07", CustomDateUtils.getCurrentMonth());
  }

  @Test
  void getCurrentDay() {
    assertEquals("02", CustomDateUtils.getCurrentDay());
  }

  @Test
  void getCurrentDateTimeMillisecond() {
    assertEquals("02", CustomDateUtils.getCurrentDateTimeMillisecond());
  }
}
