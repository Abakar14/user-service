package com.bytmasoft.dss.entities;


import com.bytmasoft.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Hierarchical Roles
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles")
public class Role extends BaseEntity implements Serializable {


@Builder.Default
@OneToMany(mappedBy = "parentRole", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<Role> childRoles = new HashSet<>();

@ManyToOne
@JoinColumn(name = "parent_role_id")
private Role parentRole;

@Column(unique = true, nullable = false)
private String name;

@Column(nullable = false)
private String description;


@Builder.Default
@Column(columnDefinition = "Boolean default false")
private boolean deleted = false;



/*@Builder.Default
@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
private Set<User> users = new HashSet<>();*/


@Builder.Default
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
		name = "role_permissions",
		joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
private Set<Permission> permissions = new HashSet<>();

}
