package kr.app.restfulapi.domain.common.role.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.app.restfulapi.domain.common.resource.entity.Resource;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resource_roles")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ResourceRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long resourceRoleId;

  @ManyToOne
  @JoinColumn(name = "resource_id")
  private Resource resource;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;
}
