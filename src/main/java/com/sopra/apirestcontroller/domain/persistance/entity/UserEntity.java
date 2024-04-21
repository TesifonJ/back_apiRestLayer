package com.sopra.apirestcontroller.domain.persistance.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @NotNull
    @JsonIgnore
    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;
    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;
    @Column(name = "credential_No_Expired")
    private boolean credentialsNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRoleEntity> roles = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, boolean isEnabled, boolean accountNoExpired,
            boolean accountNoLocked,
            boolean credentialsNoExpired, Set<UserRoleEntity> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.accountNoExpired = accountNoExpired;
        this.accountNoLocked = accountNoLocked;
        this.credentialsNoExpired = credentialsNoExpired;
        this.roles = roles;
    }

    public UserEntity(Long id, String username, String password, boolean isEnabled, boolean accountNoExpired,
            boolean accountNoLocked,
            boolean credentialsNoExpired) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.accountNoExpired = accountNoExpired;
        this.accountNoLocked = accountNoLocked;
        this.credentialsNoExpired = credentialsNoExpired;
    }

    public UserEntity(String username, String password, boolean isEnabled, boolean accountNoExpired,
            boolean accountNoLocked,
            boolean credentialsNoExpired, Set<UserRoleEntity> roles) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.accountNoExpired = accountNoExpired;
        this.accountNoLocked = accountNoLocked;
        this.credentialsNoExpired = credentialsNoExpired;
        this.roles = roles;
    }

    public UserEntity(Long id, String username, String password, UserRoleEntity role) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isAccountNoExpired() {
        return accountNoExpired;
    }

    public void setAccountNoExpired(boolean accountNoExpired) {
        this.accountNoExpired = accountNoExpired;
    }

    public boolean isAccountNoLocked() {
        return accountNoLocked;
    }

    public void setAccountNoLocked(boolean accountNoLocked) {
        this.accountNoLocked = accountNoLocked;
    }

    public boolean isCredentialsNoExpired() {
        return credentialsNoExpired;
    }

    public void setCredentialsNoExpired(boolean credentialsNoExpired) {
        this.credentialsNoExpired = credentialsNoExpired;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
    }

}
