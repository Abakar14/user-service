package com.bytmasoft.dss.dto;

import com.bytmasoft.dss.entities.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleCreateDto  {

	private String name;
	private String description;
	private Role parentRole;


}
