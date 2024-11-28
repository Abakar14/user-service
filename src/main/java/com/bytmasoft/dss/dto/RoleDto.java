package com.bytmasoft.dss.dto;


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
    private boolean deleted;
    private Set<PermissionDto> authorities;
private Boolean isActive;
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
private LocalDateTime addedOn;

@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
private LocalDateTime modifiedOn;
private String addedBy;
private String modifiedBy;


}
