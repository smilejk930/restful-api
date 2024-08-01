package kr.app.restfulapi.domain.common.user.gnrl.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import kr.app.restfulapi.domain.common.user.gnrl.entity.GnrlUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

  private static final long serialVersionUID = 1L;

  private String userTsid;
  private String lgnId;
  private String userNm;
  private String pswd;
  private Collection<? extends GrantedAuthority> authorities;

  public static UserPrincipal create(GnrlUser gnrlUser) {
    List<GrantedAuthority> authorities = gnrlUser.getUserAuthrts()
        .stream()
        .map(userAuthrt -> new SimpleGrantedAuthority(userAuthrt.getUserTypeCd().name()))
        .collect(Collectors.toList());

    return UserPrincipal.builder()
        .userTsid(gnrlUser.getUserTsid())
        .lgnId(gnrlUser.getLgnId())
        .userNm(gnrlUser.getUserNm())
        .pswd(gnrlUser.getPswd())
        .authorities(authorities)
        .build();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return pswd;
  }

  @Override
  public String getUsername() {
    return userNm;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
