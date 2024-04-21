package com.sopra.apirestcontroller.common.DTO;

import java.util.HashSet;
import java.util.Set;

import com.sopra.apirestcontroller.domain.persistance.entity.UserRoleEntity;

public class UserDto {

    private Long id;

    private String username;

    private String password;

    private Set<UserRoleEntity> roles = new HashSet<>();

    private boolean isEnabled;
    
    private boolean accountNoExpired;

    private boolean accountNoLocked;

    private boolean credentialsNoExpired;

    public UserDto() {
    }

    

    public UserDto(Long id, String username, String password, Set<UserRoleEntity> roles, boolean isEnabled,
            boolean accountNoExpired, boolean accountNoLocked, boolean credentialsNoExpired) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.isEnabled = isEnabled;
        this.accountNoExpired = accountNoExpired;
        this.accountNoLocked = accountNoLocked;
        this.credentialsNoExpired = credentialsNoExpired;
    }


    public UserDto(Long id, String username, String password, boolean isEnabled,
            boolean accountNoExpired, boolean accountNoLocked, boolean credentialsNoExpired) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.accountNoExpired = accountNoExpired;
        this.accountNoLocked = accountNoLocked;
        this.credentialsNoExpired = credentialsNoExpired;
    }

    public UserDto(Long id, String username, String password, Set<UserRoleEntity> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    

}
