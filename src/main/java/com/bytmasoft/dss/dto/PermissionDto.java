package com.bytmasoft.dss.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class PermissionDto extends PermissionCreateDto {

        private Long id;


        private boolean active;
        private boolean deleted;



}