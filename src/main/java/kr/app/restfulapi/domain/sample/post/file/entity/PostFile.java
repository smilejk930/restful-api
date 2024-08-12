package kr.app.restfulapi.domain.sample.post.file.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import kr.app.restfulapi.domain.common.file.entity.BaseFileEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "t_post_file", indexes = {@Index(name = "ix_t_post_file", columnList = "del_yn, file_group_nm, rfrnc_tsid, file_clsf_nm")})
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFile extends BaseFileEntity {

}
