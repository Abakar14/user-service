package com.bytmasoft.dss.controller;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.UserCreateDto;
import com.bytmasoft.dss.dto.UserDto;
import com.bytmasoft.dss.dto.UserUpdateDto;
import com.bytmasoft.dss.service.AuthorityServiceImpl;
import com.bytmasoft.dss.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@Validated
@AllArgsConstructor
@Schema(name = "User")
@Tag(name = "User", description = "The User Service Application used for user activities")
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserController implements UserApi {

    private final   UserServiceImpl userService;
    private final AuthorityServiceImpl authorityServiceImpl;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public ResponseEntity<UserDto> save(UserCreateDto userCreateDto) {
           return ResponseEntity.ok(userService.add(userCreateDto));
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_TEACHER')")
    @Override
    public List<UserDto> findList() {
        return  userService.findAllAsList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_TEACHER')")
    @Override
    public ResponseEntity<UserDto> findById(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Override
    public ResponseEntity<UserDto> update(Long id, @Valid UserUpdateDto userUpdateDto) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(userService.update(id, userUpdateDto));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Override
    public ResponseEntity<UserDto> delete(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(userService.delete(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public ResponseEntity<UserDto> addRoleToUser(Long userId, Long roleId) {
        return ResponseEntity.ok(userService.addRoleToUser(userId, roleId));
    }


    @Override
    public ResponseEntity<UserDto> findByUsername(String username) throws Exception {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @Override
    public ResponseEntity<UserDto> unlock(Long id) throws DSSEntityNotFoundException {

        return ResponseEntity.ok(userService.unlock(id));
    }

    @Override
    public ResponseEntity<UserDto> lockout(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(userService.lockout(id));
    }

    @Override
    public ResponseEntity<UserDto> markfordeletion(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(userService.markfordeletion(id));
    }

    @Override
    public Long countAll() {
        return userService.countAllUsers();
    }

    @Override
    public Long countAllActives() {
        return userService.countAllActiveUsers();
    }

    @Override
    public Long countAllLocked() {
        return userService.countAllLockedUsers();
    }
}
