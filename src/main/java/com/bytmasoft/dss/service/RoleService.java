package com.bytmasoft.dss.service;


import com.bytmasoft.common.service.ServiceApi;
import com.bytmasoft.dss.dto.RoleCreateDto;
import com.bytmasoft.dss.dto.RoleDto;
import com.bytmasoft.dss.dto.RoleUpdateDto;


public interface RoleService extends ServiceApi<RoleDto, RoleCreateDto, RoleUpdateDto> {
    RoleDto addAuthorityToRole(Long roleId, Long authorityId);

    int count();

    Long countRoles();

    Long countActiveRoles();

    Long countLockedRoles();
}
