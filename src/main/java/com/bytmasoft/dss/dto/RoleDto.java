package com.bytmasoft.dss.dto;


import com.bytmasoft.dss.entities.Permission;
import com.bytmasoft.dss.entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {

private Long id;
private String name;
private String description;
private Set<Long> childRoleIds;
private Long parentRoleId;
private Set<Long> permissionIds;
private boolean deleted;
private Boolean isActive;
private LocalDateTime addedOn;
private LocalDateTime modifiedOn;
private String addedBy;
private String modifiedBy;


}
