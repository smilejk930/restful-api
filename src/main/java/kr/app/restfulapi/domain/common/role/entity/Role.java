package kr.app.restfulapi.domain.common.role.entity;

import java.util.HashSet;
import java.util.Set;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import kr.app.restfulapi.domain.common.resource.entity.Resource;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Role {

  @Id
  @Tsid
  @Column(length = 13, columnDefinition = "CHAR(13)")
  private String roleId;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  // @ManyToMany(mappedBy = "roles")
  // private Set<GnrlUser> gnrlUsers = new HashSet<>();

  @ManyToMany(mappedBy = "roles")
  private Set<Resource> resources = new HashSet<>();

}

