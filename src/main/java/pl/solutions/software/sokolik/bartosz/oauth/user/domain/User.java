package pl.solutions.software.sokolik.bartosz.oauth.user.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@EqualsAndHashCode(of = "uuid")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uuid")
  private String uuid = UUID.randomUUID().toString();

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "account_expired")
  private boolean accountExpired;

  @Column(name = "account_locked")
  private boolean accountLocked;

  @Column(name = "credentials_expired")
  private boolean credentialsExpired;

  @Column(name = "enabled")
  private boolean enabled;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_authorities",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
  private Set<Authority> authorities = new HashSet<>();

  @Override
  public Collection<Authority> getAuthorities() {
    return authorities;
  }

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
