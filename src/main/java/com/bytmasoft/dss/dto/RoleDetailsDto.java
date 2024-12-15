package com.bytmasoft.dss.dto;


import com.bytmasoft.dss.entities.Permission;
import com.bytmasoft.dss.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDetailsDto {

private Long id;
private String name;
private String description;
private Set<Role> childRoles;
private Role parentRole;
private Set<Permission> permissions;
private boolean deleted;
private Boolean isActive;
private LocalDateTime addedOn;
private LocalDateTime modifiedOn;
private String addedBy;
private String modifiedBy;


}
