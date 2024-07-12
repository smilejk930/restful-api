package kr.app.restfulapi.domain.common.user.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import kr.app.restfulapi.domain.common.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

  private static final long serialVersionUID = 1L;

  private String userId;
  private String loginId;
  private String userNm;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public static UserPrincipal create(User user) {
    List<GrantedAuthority> authorities =
        user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    return UserPrincipal.builder()
        .userId(user.getUserId())
        .loginId(user.getLoginId())
        .userNm(user.getUserNm())
        .password(user.getPassword())
        .authorities(authorities)
        .build();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
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
