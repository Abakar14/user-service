package com.bytmasoft.dss.dto;


import com.bytmasoft.dss.entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDto {

private Long id;
private String name;
private String description;
private boolean deleted;
private Boolean isActive;
private LocalDateTime addedOn;
private LocalDateTime modifiedOn;
private String addedBy;
private String modifiedBy;
private Set<Role> roles;


}