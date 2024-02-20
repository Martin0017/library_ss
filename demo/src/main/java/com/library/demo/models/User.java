package com.library.demo.models;
import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name="Users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idUser")
  private Long idUser;

  @NotEmpty
  @Column(name = "nameUser")
  private String nameUser;

  @NotEmpty
  @Column(name = "emailUser")
  private String emailUser;

  @NotEmpty
  @Column(name = "passwordUser")
  private String passwordUser;

  public String getEmailUser() {
    return emailUser;
}

public User(Long idUser, @NotEmpty String nameUser, @NotEmpty String emailUser, @NotEmpty String passwordUser,
        @NotEmpty String roleUser, @NotEmpty String lastnameUser) {
    this.idUser = idUser;
    this.nameUser = nameUser;
    this.emailUser = emailUser;
    this.passwordUser = passwordUser;
    this.roleUser = roleUser;
    this.lastnameUser = lastnameUser;
}

public void setEmailUser(String emailUser) {
    this.emailUser = emailUser;
}

public String getPasswordUser() {
    return passwordUser;
}

public void setPasswordUser(String passwordUser) {
    this.passwordUser = passwordUser;
}

public String getRoleUser() {
    return roleUser;
}

public void setRoleUser(String roleUser) {
    this.roleUser = roleUser;
}

@NotEmpty
  @Column(name = "roleUser")
  private String roleUser;

 @JsonIgnore
  @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
  private Set<UserHasBooks> userHasBook = new HashSet<>();
  
  public User() {
}

public Long getIdUser() {
    return idUser;
}

public void setIdUser(Long idUser) {
    this.idUser = idUser;
}

public String getNameUser() {
    return nameUser;
}

public Set<UserHasBooks> getUserHasBook() {
    return userHasBook;
}

public void setUserHasBook(Set<UserHasBooks> userHasBook) {
    this.userHasBook = userHasBook;
}

public void setNameUser(String nameUser) {
    this.nameUser = nameUser;
}

public String getLastnameUser() {
    return lastnameUser;
}

public void setLastnameUser(String lastnameUser) {
    this.lastnameUser = lastnameUser;
}

@NotEmpty
  @Column(name = "lastnameUsers")
  private String lastnameUser;

} 