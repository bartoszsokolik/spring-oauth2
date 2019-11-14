package pl.solutions.software.sokolik.bartosz.oauth.user.domain;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnotherUser implements UserDetails {

  private String username;

  private String password;

  private boolean accountExpired;

  private boolean accountLocked;

  private boolean credentialsExpired;

  private boolean enabled;

  private Set<Authority> authorities = new HashSet<>();

  @Override
  public boolean isAccountNonExpired() {
    return !isAccountExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return !isAccountLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !isCredentialsExpired();
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
