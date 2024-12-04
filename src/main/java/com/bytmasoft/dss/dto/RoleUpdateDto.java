package com.bytmasoft.dss.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleUpdateDto {


@Size(min = 3, max = 50)
private String name;


@Size(min = 3, max = 250)
private String description;

private Long parent_role_id;

private Set<Long> permissionIds;

}
