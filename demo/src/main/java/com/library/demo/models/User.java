package com.library.demo.models;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Users")
public class User implements UserDetails, Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idUser")
  private Long idUser;

  @NotEmpty
  @Column(name = "nameUser")
  private String userName;

  @NotEmpty
  @Column(name = "emailUser")
  private String emailUser;

  @NotEmpty
  @Column(name = "passwordUser")
  private String password;

  @Column(name = "roleUser")
  private Role roleUser;

  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleUser.name()));
    }

  @NotEmpty
  @Column(name = "lastnameUsers")
  private String lastnameUser;

  public String getEmailUser() {
    return emailUser;
}

public User(Long idUser, @NotEmpty String nameUser, @NotEmpty String emailUser, @NotEmpty String passwordUser,
        Role roleUser, @NotEmpty String lastnameUser) {
    this.idUser = idUser;
    this.userName = nameUser;
    this.emailUser = emailUser;
    this.password = passwordUser;
    this.roleUser = roleUser;
    this.lastnameUser = lastnameUser;
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

public void setEmailUser(String emailUser) {
    this.emailUser = emailUser;
}

public String getPassword() {
    return password;
}

public void setPassword(String passwordUser) {
    this.password = passwordUser;
}

public Role getRoleUser() {
    return roleUser;
}

public void setRoleUser(Role roleUser) {
    this.roleUser = roleUser;
}



@JsonIgnore
@OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
private Set<UserHasBooks> userHasBook = new HashSet<>();
  
public Long getIdUser() {
    return idUser;
}

public void setIdUser(Long idUser) {
    this.idUser = idUser;
}

public String getUsername() {
    return userName;
}

public Set<UserHasBooks> getUserHasBook() {
    return userHasBook;
}

public void setUserHasBook(Set<UserHasBooks> userHasBook) {
    this.userHasBook = userHasBook;
}

public void setNameUser(String nameUser) {
    this.userName = nameUser;
}

public String getLastnameUser() {
    return lastnameUser;
}

public void setLastnameUser(String lastnameUser) {
    this.lastnameUser = lastnameUser;
}



} 