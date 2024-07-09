package kr.app.restfulapi.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomDateUtils {

  /**
   * @return yyyyMMdd
   */
  public static String getCurrentDate() {
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    return now.format(formatter);
  }

  /**
   * @return yyyy
   */
  public static String getCurrentYear() {
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
    return now.format(formatter);
  }

  /**
   * @return MM
   */
  public static String getCurrentMonth() {
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
    return now.format(formatter);
  }

  /**
   * @return dd
   */
  public static String getCurrentDay() {
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
    return now.format(formatter);
  }

  /**
   * @return yyyyMMddhhmmssSSS
   */
  public static String getCurrentDateTimeMillisecond() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS");
    return now.format(formatter);
  }

  /**
   * @return yyyy-MM-dd HH:mm:ss
   */
  public static String getFormatCurrentDateTime() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return now.format(formatter);
  }
}
