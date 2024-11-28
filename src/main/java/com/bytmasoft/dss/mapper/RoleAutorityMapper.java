package com.bytmasoft.dss.mapper;

import com.bytmasoft.dss.dto.RoleAuthorityCreateDto;
import com.bytmasoft.dss.dto.RoleAuthorityDto;
import com.bytmasoft.dss.dto.RoleAuthorityUpdateDto;
import com.bytmasoft.dss.entities.RoleAuthority;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleAutorityMapper {

    RoleAuthorityDto entityToDto(RoleAuthority roleAuthority);

    RoleAuthority dtoToEntity(RoleAuthorityDto roleAuthorityDto);


    RoleAuthority dtoCreateToEntity(RoleAuthorityCreateDto roleAuthorityCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoleAuthority partialUpdate(RoleAuthorityUpdateDto authorityUpdateDto, @MappingTarget RoleAuthority roleAuthority);

}
