package com.bytmasoft.dss.controller;

import com.bytmasoft.common.controller.DSSCrud;
import com.bytmasoft.dss.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface RoleAuthorityApi extends DSSCrud<RoleAuthorityDto, RoleAuthorityCreateDto, RoleAuthorityUpdateDto> {

    @PostMapping("{role_id}/autorities/{authority_id}")
    ResponseEntity<RoleDto> addAuthorityToRole(@PathVariable Long role_id , Long authority_id);

/*    @GetMapping("/count")
    Long countAllRoles();

    @GetMapping("/active/count")
    Long countAllActiveRoles();

    @GetMapping("/locked/count")
    Long countAllLockedRoles();*/

}
