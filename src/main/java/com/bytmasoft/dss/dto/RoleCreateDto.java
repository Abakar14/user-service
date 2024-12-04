package com.bytmasoft.dss.dto;

import com.bytmasoft.dss.entities.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleCreateDto {

@NotNull
@Size(min = 3, max = 50)
private String name;


@Size(min = 3, max = 250)
private String description;

private Long parent_role_id;

private Set<Long> permissionIds;


}
