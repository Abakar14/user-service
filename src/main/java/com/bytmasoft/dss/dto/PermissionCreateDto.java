package com.bytmasoft.dss.dto;

import com.bytmasoft.dss.entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionCreateDto {

@NotNull
@Size(min = 3, max = 50)
private String name;

@Size(min = 3, max = 250)
private String description;

//private Set<Role> roles;

//private boolean deleted;

}
