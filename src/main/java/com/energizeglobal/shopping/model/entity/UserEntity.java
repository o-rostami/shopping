package com.energizeglobal.shopping.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Audited
@Setter
@Getter
@Entity
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "USER";

    @Column(name = "USER_NAME",unique = true)
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ENABLED")
    private boolean blocked;
    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<RoleEntity> roles = new HashSet<>();
}
