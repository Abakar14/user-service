package com.bytmasoft.dss.controller;

import com.bytmasoft.common.controller.DSSCrud;
import com.bytmasoft.dss.dto.PermissionCreateDto;
import com.bytmasoft.dss.dto.PermissionDto;
import com.bytmasoft.dss.dto.PermissionUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


public interface PermissionApi extends DSSCrud<PermissionDto, PermissionCreateDto, PermissionUpdateDto> {

    @PostMapping("{authority_id}/roles/{role_id}")
    ResponseEntity<PermissionDto> addAuthorityToRole(@PathVariable Long authority_id, @PathVariable Long role_id);

 /*   @GetMapping("/count")
    Long countAuthorities();*/

    /*@GetMapping("/active/count")
    Long countActiveAuthorities();*/

/*    @GetMapping("/locked/count")
    Long countLockedAuthorities();*/

}
