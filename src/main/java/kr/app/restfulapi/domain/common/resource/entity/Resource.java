package kr.app.restfulapi.domain.common.resource.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.app.restfulapi.domain.common.role.entity.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resources", schema = "TESTUSER")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Resource {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long resourceId;

  @Column(nullable = false)
  private String urlPattern;

  @Column(nullable = false)
  private String httpMethod;

  @Column()
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_resource_id")
  // @JsonBackReference // 부모 엔티티를 참조하는 필드에 설정
  private Resource parent;

  @OneToMany(mappedBy = "parent")
  // @JsonManagedReference // 자식 엔티티들을 참조하는 필드에 설정
  private List<Resource> children = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "resource_roles", joinColumns = @JoinColumn(name = "resource_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();
}

