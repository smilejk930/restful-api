package kr.app.restfulapi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import kr.app.restfulapi.uga.common.util.DateUtils;

class DateUtilsTest {

  @Test
  void getCurrentDate() {
    assertEquals("20240702", DateUtils.getCurrentDate());
  }

  @Test
  void getCurrentYear() {
    assertEquals("2024", DateUtils.getCurrentYear());
  }

  @Test
  void getCurrentMonth() {
    assertEquals("07", DateUtils.getCurrentMonth());
  }

  @Test
  void getCurrentDay() {
    assertEquals("02", DateUtils.getCurrentDay());
  }

  @Test
  void getCurrentDateTimeMillisecond() {
    assertEquals("02", DateUtils.getCurrentDateTimeMillisecond());
  }
}
