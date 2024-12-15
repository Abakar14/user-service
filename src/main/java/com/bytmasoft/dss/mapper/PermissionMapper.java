package com.bytmasoft.dss.mapper;

import com.bytmasoft.dss.dto.PermissionCreateDto;
import com.bytmasoft.dss.dto.PermissionDto;
import com.bytmasoft.dss.dto.PermissionUpdateDto;
import com.bytmasoft.dss.dto.RoleDto;
import com.bytmasoft.dss.entities.Permission;
import com.bytmasoft.dss.entities.Role;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface PermissionMapper {


    @Mapping(target = "roles", ignore = true)
    PermissionDto toPermissionDto(Permission permission);

    @Mapping(target = "roles", ignore = true)
    Permission toPermission(PermissionDto permissionDto);

    List<PermissionDto> toPermissionDtoList(List<Permission> permissions);

    List<Permission> toPermissionList(List<PermissionDto> permissionDtos);

@Mapping(target = "roles", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Permission dtoCreateToEntity(PermissionCreateDto permissionCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "addedOn", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "addedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Permission partialUpdate(PermissionUpdateDto updateDto, @MappingTarget Permission permission);
}


