package com.bytmasoft.dss.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class PermissionUpdateDto implements Serializable {
    private String name;

}
