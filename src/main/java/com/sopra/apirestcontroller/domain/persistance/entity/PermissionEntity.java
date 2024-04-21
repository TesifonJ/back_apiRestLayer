package com.sopra.apirestcontroller.domain.persistance.entity;

import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name = "permissions")
public class PermissionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "user_role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissionList = new HashSet<>();

    public PermissionEntity() {
    }

    

    public PermissionEntity(Long id, String name, Set<PermissionEntity> permissionList) {
        this.id = id;
        this.name = name;
        this.permissionList = permissionList;
    }



    public PermissionEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
