package kr.app.restfulapi.domain.sample.post.dto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record PostSearchDto(
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pstgBgngYmd,
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pstgEndYmd) {

}
