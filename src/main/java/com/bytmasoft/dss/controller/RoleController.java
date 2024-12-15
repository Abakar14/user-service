package com.bytmasoft.dss.controller;

import com.bytmasoft.common.controller.DSSCrud;
import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.RoleCreateDto;
import com.bytmasoft.dss.dto.RoleDetailsDto;
import com.bytmasoft.dss.dto.RoleDto;
import com.bytmasoft.dss.dto.RoleUpdateDto;
import com.bytmasoft.dss.repository.RoleRepository;
import com.bytmasoft.dss.service.RoleService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@AllArgsConstructor
@Schema(name = "Role")
@Tag(name = "Role", description = "The Role Controller  used for add Role to user")
@RequestMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RoleController implements DSSCrud<RoleDto, RoleCreateDto, RoleUpdateDto> {

private final RoleService roleService;
private final RoleRepository roleRepository;

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<RoleDto> save(@Valid RoleCreateDto roleCreateDto) {
	return ResponseEntity.ok(roleService.add(roleCreateDto));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public List<RoleDto> findList() {
	return roleService.findAllAsList();
}


@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@GetMapping({"/details"})
public List<RoleDetailsDto> findDetailsRoles() {
	return roleService.findDetailsRoles();
}



@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<RoleDto> findById(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(roleService.findById(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@GetMapping({"/{id}/details"})
public ResponseEntity<RoleDetailsDto> findRoleDetailsById(@PathVariable Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(roleService.findRoleDetailsById(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<RoleDto> update(Long id, @Valid RoleUpdateDto roleUpdateDto) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(roleService.update(id, roleUpdateDto));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<RoleDto> delete(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(roleService.delete(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@PostMapping("{role_id}/permissions/{permission_id}")
ResponseEntity<RoleDto> addPermissionToRole(@PathVariable Long role_id , @PathVariable Long permission_id){
	return ResponseEntity.ok(roleService.addPermissionToRole(role_id, permission_id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@PostMapping("{role_id}/permissions")
ResponseEntity<RoleDto> addPermissionToRole(@PathVariable Long role_id , @RequestParam List<Long> permissionIds){
	return ResponseEntity.ok(roleService.addPermissionsToRole(role_id, permissionIds));
}




@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<RoleDto> unlock(Long aLong) throws DSSEntityNotFoundException {
	return null;
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<RoleDto> lockout(Long aLong) throws DSSEntityNotFoundException {
	return null;
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<RoleDto> markfordeletion(Long aLong) throws DSSEntityNotFoundException {
	return null;
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public Long countAll() {
	return roleRepository.count();
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public Long countAllActives() {
	return roleService.countActiveRoles();
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public Long countAllLocked() {
	return roleService.countLockedRoles();
}

}
