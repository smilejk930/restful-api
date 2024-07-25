package kr.app.restfulapi.domain.common.file.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.LastModifiedDate;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import kr.app.restfulapi.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(indexes = {@Index(name = "idx_finm", columnList = "finm"), @Index(name = "idx_regist_dt", columnList = "regist_dt")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileData extends BaseEntity {

  @Comment("파일아이디")
  @Id
  @Tsid
  @Column(length = 13, columnDefinition = "CHAR(13)")
  private String fileId;

  @Comment("파일명")
  @Column(length = 200, nullable = false)
  private String fileNm;

  @Comment("파일그룹명")
  @Column(length = 200, nullable = false)
  private String fileGroupNm;

  @Comment("참조아이디")
  @Column(length = 13, columnDefinition = "CHAR(13)", nullable = false)
  private String refrnId;

  @Comment("파일섹션값")
  @Column(nullable = false)
  private String fileSectValue;

  @Comment("삭제여부")
  @ColumnDefault("'N'")
  @Column(length = 1, nullable = false)
  @Builder.Default
  private String deleteAt = "N";

  @Comment("파일순번")
  @ColumnDefault("0")
  @Column(nullable = false)
  @Builder.Default
  private Long fileSn = 0L;

  @Comment("파일저장명")
  @Column(length = 200, nullable = false)
  private String fileStreNm;

  @Comment("파일저장경로")
  @Column(length = 4000, nullable = false)
  private String fileStreCours;

  @Comment("파일확장자명")
  @Column(length = 200, nullable = false)
  private String fileExtsnNm;

  @Comment("파일사이즈")
  @Column(nullable = false)
  private Long fileSize;

  @Comment("다운로드횟수")
  @ColumnDefault("0")
  @Column(nullable = false)
  @Builder.Default
  private Long dwldCo = 0L;

  @Comment("파일동기화코드")
  @ColumnDefault("'FSC001'")
  @Column(nullable = false)
  @Builder.Default
  private String fileSynchrnCode = "FSC001";

  @Comment("파일동기화일시")
  @LastModifiedDate
  private LocalDateTime fileSynchrnDt;
}
