package kr.app.restfulapi.domain.common.file.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.LastModifiedDate;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import kr.app.restfulapi.domain.common.file.util.FileGroupNmType;
import kr.app.restfulapi.domain.common.file.util.FileSyncType;
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
  @Comment("파일식별번호")
  @Column(length = 13, columnDefinition = "CHAR(13)")
  private String fileTsid;

  @Comment("파일명")
  @Column(length = 100, nullable = false)
  private String fileNm;

  @Enumerated(EnumType.STRING)
  @Comment("파일그룹명")
  @Column(length = 100, nullable = false)
  private FileGroupNmType fileGroupNm;

  @Comment("참조식별번호")
  @Column(length = 13, columnDefinition = "CHAR(13)", nullable = false)
  private String rfrncTsid;

  @Comment("파일분류명")
  @Column(length = 100, nullable = false)
  private String fileClsfNm;

  @Comment("삭제여부")
  @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
  @ColumnDefault("'N'")
  @Builder.Default
  private String delYn = "N";

  @Comment("파일순서")
  @Column(nullable = false)
  @ColumnDefault("0")
  @Builder.Default
  private Long fileSeq = 0L;

  @Comment("저장파일명")
  @Column(length = 300, nullable = false)
  private String strgFileNm;

  @Comment("파일저장경로")
  @Column(length = 100, nullable = false)
  private String fileStrgPath;

  @Comment("파일확장자명")
  @Column(length = 50, nullable = false)
  private String fileExtnNm;

  @Comment("파일사이즈")
  @Column(nullable = false)
  private Long fileSize;

  @Comment("다운로드수")
  @Column(nullable = false)
  @ColumnDefault("0")
  @Builder.Default
  private Long dwnldCnt = 0L;

  @Enumerated(EnumType.STRING)
  @Comment("파일동기화코드")
  @Column(length = 10, nullable = false)
  @ColumnDefault("'FSC001'")
  @Builder.Default
  private FileSyncType fileSyncCd = FileSyncType.FSC001;

  @Comment("파일동기화일시")
  @LastModifiedDate
  private LocalDateTime fileSyncDt;
}
