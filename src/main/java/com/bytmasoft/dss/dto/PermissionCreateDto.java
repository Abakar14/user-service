package com.bytmasoft.dss.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PermissionCreateDto extends PermissionUpdateDto {
}
