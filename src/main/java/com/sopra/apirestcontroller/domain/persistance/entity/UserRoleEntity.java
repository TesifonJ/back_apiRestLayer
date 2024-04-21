package com.sopra.apirestcontroller.domain.persistance.entity;

import java.util.HashSet;
import java.util.Set;

import com.sopra.apirestcontroller.domain.persistance.entity.enumEntity.RoleUserEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity
@Table(name = "roles")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank
    @Column(name = "role_name", unique = true)
    @Enumerated(EnumType.STRING)
    private RoleUserEnum roleUserEnum;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissionList = new HashSet<>();

    public UserRoleEntity(RoleUserEnum roleUserEnum, Set<PermissionEntity> permissionList) {
        this.roleUserEnum = roleUserEnum;
        this.permissionList = permissionList;
    }

    public UserRoleEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleUserEnum getRoleUserEnum() {
        return roleUserEnum;
    }

    public void setRoleUserEnum(RoleUserEnum roleUserEnum) {
        this.roleUserEnum = roleUserEnum;
    }

    public Set<PermissionEntity> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<PermissionEntity> permissionList) {
        this.permissionList = permissionList;
    }

    

}
