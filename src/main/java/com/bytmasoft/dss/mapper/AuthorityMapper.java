package com.bytmasoft.dss.mapper;

import com.bytmasoft.dss.dto.PermissionCreateDto;
import com.bytmasoft.dss.dto.PermissionDto;
import com.bytmasoft.dss.dto.PermissionUpdateDto;
import com.bytmasoft.dss.entities.Permission;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorityMapper {

//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "id", source = "id")
    PermissionDto entityToDto(Permission authority);

    Permission dtoToEntity(PermissionDto authorityDto);


    Permission dtoCreateToEntity(PermissionCreateDto authorityCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Permission partialUpdate(PermissionUpdateDto var1, @MappingTarget Permission var2);

}


