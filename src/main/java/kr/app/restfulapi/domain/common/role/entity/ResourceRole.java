package kr.app.restfulapi.domain.common.role.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.app.restfulapi.domain.common.resource.entity.Resource;

@Entity
@Table(name = "resource_roles", schema = "TESTUSER")
public class ResourceRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long resourceRolesId;

  @ManyToOne
  @JoinColumn(name = "resource_id", nullable = false)
  private Resource resource;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  // constructors, getters, setters
}
