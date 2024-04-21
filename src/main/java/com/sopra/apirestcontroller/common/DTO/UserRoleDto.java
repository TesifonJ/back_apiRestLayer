package com.sopra.apirestcontroller.common.DTO;

import java.util.HashSet;
import java.util.Set;

import com.sopra.apirestcontroller.domain.persistance.entity.UserRoleEntity;

public class UserRoleDto {

    private long id;

    private String userRoleName;

    private Set<UserRoleEntity> users = new HashSet<UserRoleEntity>();

    public UserRoleDto() {
    }

    public UserRoleDto(long id, String userRoleName, Set<UserRoleEntity> users) {
        this.id = id;
        this.userRoleName = userRoleName;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public Set<UserRoleEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserRoleEntity> users) {
        this.users = users;
    }

}
