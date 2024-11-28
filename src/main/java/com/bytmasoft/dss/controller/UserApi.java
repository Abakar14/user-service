package com.bytmasoft.dss.controller;

import com.bytmasoft.common.controller.DSSCrud;
import com.bytmasoft.dss.dto.UserCreateDto;
import com.bytmasoft.dss.dto.UserDto;
import com.bytmasoft.dss.dto.UserUpdateDto;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


public interface UserApi extends DSSCrud<UserDto, UserCreateDto, UserUpdateDto> {

    @PostMapping("{userId}/roles/{roleId}")
    ResponseEntity<UserDto> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId);

    @GetMapping("/username")
    ResponseEntity<UserDto> findByUsername(@PathParam("username") String username) throws Exception;

   /* @GetMapping("/count")
    Long countAllUsers();

    @GetMapping("/active/count")
    Long countAllActiveUsers();

    @GetMapping("/locked/count")
    Long countAllLockedUsers();*/

}
