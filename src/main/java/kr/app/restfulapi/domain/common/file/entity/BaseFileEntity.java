package kr.app.restfulapi.domain.common.file.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.LastModifiedDate;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import kr.app.restfulapi.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseFileEntity extends BaseEntity {

  public abstract BaseFileEntity.BaseFileEntityBuilder<?, ?> toBuilder();

  @Id
  @Tsid
  @Comment("파일아이디")
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
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String delYn = "N";

  @Comment("파일순번")
  @Column(nullable = false)
  @ColumnDefault("0")
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
  @Column(nullable = false)
  @ColumnDefault("0")
  @Builder.Default
  private Long dwldCo = 0L;

  @Comment("파일동기화코드")
  @Column(nullable = false)
  @ColumnDefault("'FSC001'")
  @Builder.Default
  private String fileSynchrnCode = "FSC001";

  @Comment("파일동기화일시")
  @LastModifiedDate
  private LocalDateTime fileSynchrnDt;
}
