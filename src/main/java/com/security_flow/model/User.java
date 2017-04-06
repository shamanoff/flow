package com.security_flow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;
  @Column(name = "user_name")
  private String username;
  @Column(name = "user_password")
  private String password;

  @Transient
  private String confirmPassword;


  @ManyToMany(fetch = EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "user_role", referencedColumnName = "id")
  )
  private List<Roles> roles;


  public User() {
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password=" + password +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return Objects.equals(getId(), user.getId()) &&
            Objects.equals(getUsername(), user.getUsername()) &&
            Objects.equals(getPassword(), user.getPassword());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUsername(),  getPassword());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public String getUsername() {
    return this.username;
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


  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
