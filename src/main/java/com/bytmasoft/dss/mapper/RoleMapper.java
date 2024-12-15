package com.bytmasoft.dss.mapper;

import com.bytmasoft.common.entities.BaseEntity;
import com.bytmasoft.dss.dto.*;
import com.bytmasoft.dss.entities.Permission;
import com.bytmasoft.dss.entities.Role;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface RoleMapper {

// Mapping Role -> RoleDto
@Mapping(target = "childRoleIds", source = "childRoles", qualifiedByName = "mapToIds")
@Mapping(target = "parentRoleId", source = "parentRole.id")
@Mapping(target = "permissionIds", source = "permissions", qualifiedByName = "mapToIds")
RoleDto toRoleDto(Role role);
// Mapping RoleDto -> Role
@Mapping(target = "childRoles", ignore = true) // You can handle this later
@Mapping(target = "parentRole", ignore = true) // Optional for now
@Mapping(target = "permissions", source = "permissionIds", qualifiedByName = "mapToPermissions")
Role toRole(RoleDto roleDto);

List<RoleDto> toRoleDtos(List<Role> roles);

List<Role> toRoles(List<RoleDto> roleDtos);

RoleDetailsDto toRoleDetails(Role role);

@Mapping(target = "childRoles", ignore = true) // You can handle this later
@Mapping(target = "parentRole", ignore = true)
@Mapping(target = "permissions", ignore = true)
@Mapping(target = "deleted", ignore = true)
Role dtoCreateToEntity(RoleCreateDto roleCreateDto);


@Mapping(target = "id", ignore = true)
@Mapping(target = "isActive", ignore = true)
@Mapping(target = "addedOn", ignore = true)
@Mapping(target = "modifiedOn", ignore = true)
@Mapping(target = "addedBy", ignore = true)
@Mapping(target = "modifiedBy", ignore = true)
@Mapping(target = "childRoles", ignore = true)
@Mapping(target = "parentRole", ignore = true)
@Mapping(target = "deleted", ignore = true)
@Mapping(target = "permissions", ignore = true)
@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
Role partialUpdate(RoleUpdateDto updateDto, @MappingTarget Role role);

// Helper for converting Set<Long> -> Set<Permission>
@Named("mapToPermissions")
static Set<Permission> mapToPermissions(Set<Long> permissionIds) {
    if (permissionIds == null) return new HashSet<>();
    return permissionIds.stream()
                   .map(id -> {
                       Permission permission = new Permission();
                       permission.setId(id);
                       return permission;
                   })
                   .collect(Collectors.toSet());
}

@Named("mapToIds")
static Set<Long> mapToIds(Set<? extends BaseEntity> entities) {
    return entities == null ? null : entities.stream().map(BaseEntity::getId).collect(Collectors.toSet());
}



}
