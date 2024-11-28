package com.bytmasoft.dss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleUpdateDto {
    private String name;
}
