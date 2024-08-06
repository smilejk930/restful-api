package kr.app.restfulapi.domain.sample.post.file.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import kr.app.restfulapi.domain.common.file.entity.BaseFileEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(indexes = {@Index(name = "idx_finm", columnList = "finm"), @Index(name = "idx_reg_dt", columnList = "reg_dt")})
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFile extends BaseFileEntity {

}
