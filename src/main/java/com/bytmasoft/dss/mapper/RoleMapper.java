package com.bytmasoft.dss.mapper;

import com.bytmasoft.dss.dto.RoleCreateDto;
import com.bytmasoft.dss.dto.RoleDto;
import com.bytmasoft.dss.dto.RoleUpdateDto;
import com.bytmasoft.dss.entities.Role;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    RoleDto entityToDto(Role role);

    Role dtoToEntity(RoleDto roleDto);


    Role dtoCreateToEntity(RoleCreateDto roleCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleUpdateDto var1, @MappingTarget Role var2);

}
