package com.bytmasoft.dss.controller;

import com.bytmasoft.common.controller.DSSCrud;
import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.UserCreateDto;
import com.bytmasoft.dss.dto.UserDto;
import com.bytmasoft.dss.dto.UserUpdateDto;
import com.bytmasoft.dss.entities.ForgotPasswordRequest;
import com.bytmasoft.dss.service.PermissionService;
import com.bytmasoft.dss.service.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@AllArgsConstructor
@Schema(name = "User")
@Tag(name = "User", description = "The User Service Application used for user activities")
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserController implements DSSCrud<UserDto, UserCreateDto, UserUpdateDto> {

private final UserService userService;
private final PermissionService authorityServiceImpl;

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<UserDto> save(UserCreateDto userCreateDto) {
	return ResponseEntity.status(HttpStatus.CREATED).body(userService.add(userCreateDto));
}


@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public List<UserDto> findList() {
	return userService.findAllAsList();
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<UserDto> findById(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(userService.findById(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<UserDto> update(Long id, @Valid UserUpdateDto userUpdateDto) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(userService.update(id, userUpdateDto));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<UserDto> delete(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(userService.delete(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@PostMapping("{userId}/roles/{roleId}")
public ResponseEntity<UserDto> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId)
{
	return ResponseEntity.ok(userService.addRoleToUser(userId, roleId));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@GetMapping("/username")
public ResponseEntity<UserDto> findByUsername(@PathParam("username") String username) throws Exception
{
	return ResponseEntity.ok(userService.findByUsername(username));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<UserDto> unlock(Long id) throws DSSEntityNotFoundException {

	return ResponseEntity.ok(userService.unlock(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<UserDto> lockout(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(userService.lockout(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public ResponseEntity<UserDto> markfordeletion(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(userService.markfordeletion(id));
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public Long countAll() {
	return userService.countAllUsers();
}

@Override
public Long countAllActives() {
	return userService.countAllActiveUsers();
}

@PreAuthorize("hasAnyAuthority('MANAGE_USERS')")
@Override
public Long countAllLocked() {
	return userService.countAllLockedUsers();
}

@PostMapping("/forgot-password")
public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
	userService.processForgotPassword(request.getEmail());
	return ResponseEntity.ok("Password reset email sent if the email exists.");
}

}
