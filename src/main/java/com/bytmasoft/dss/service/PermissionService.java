package com.bytmasoft.dss.service;


import com.bytmasoft.common.service.ServiceApi;
import com.bytmasoft.dss.dto.PermissionCreateDto;
import com.bytmasoft.dss.dto.PermissionDto;
import com.bytmasoft.dss.dto.PermissionUpdateDto;
import com.bytmasoft.dss.dto.RoleCreateDto;


public interface PermissionService extends ServiceApi<PermissionDto, PermissionCreateDto, PermissionUpdateDto> {

    PermissionDto addPermissionToRole(Long groupId, Long roleId);
    PermissionDto addPermissionToRole(Long groupId, RoleCreateDto roleCreateDto);

    Long countActiveAuthorities();

    Long countPermissions();

    Long countLockedAuthorities();

    PermissionDto unlock(Long id);

    PermissionDto lockout(Long id);

    PermissionDto markfordeletion(Long id);

    Long countAllActives();

    Long countAllLocked();
}
