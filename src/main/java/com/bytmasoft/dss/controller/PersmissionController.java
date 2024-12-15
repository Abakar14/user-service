package com.bytmasoft.dss.controller;

import com.bytmasoft.common.controller.DSSCrud;
import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.PermissionCreateDto;
import com.bytmasoft.dss.dto.PermissionDto;
import com.bytmasoft.dss.dto.PermissionUpdateDto;
import com.bytmasoft.dss.service.PermissionService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;




@Validated
@AllArgsConstructor
@Schema(name = "Permission")
@Tag(name = "Permission", description = "The Permission Service used to manage the Permissions")
@RequestMapping(value = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PersmissionController implements DSSCrud<PermissionDto, PermissionCreateDto, PermissionUpdateDto> {

private final PermissionService permissionService;

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<PermissionDto> save(@Valid PermissionCreateDto permissionCreateDto) {
	return ResponseEntity.ok(permissionService.add(permissionCreateDto));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public List<PermissionDto> findList() {
	return permissionService.findAllAsList();
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<PermissionDto> findById(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(permissionService.findById(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<PermissionDto> update(Long id, @Valid PermissionUpdateDto permissionUpdateDto) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(permissionService.update(id, permissionUpdateDto));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<PermissionDto> delete(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(permissionService.delete(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@PostMapping("{permission_id}/roles/{role_id}")
public ResponseEntity<PermissionDto> addPermissionToRole(@PathVariable Long permission_id, @PathVariable Long role_id){
	return ResponseEntity.ok(permissionService.addPermissionToRole(permission_id, role_id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<PermissionDto> unlock(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(permissionService.unlock(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<PermissionDto> lockout(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(permissionService.lockout(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<PermissionDto> markfordeletion(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(permissionService.markfordeletion(id));
}

@Override
@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
public Long countAll() {
	return permissionService.countPermissions();
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public Long countAllActives() {
	return permissionService.countAllActives();
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public Long countAllLocked() {
	return permissionService.countAllLocked();
}


}



