package com.bytmasoft.dss.service;


import com.bytmasoft.common.service.ServiceApi;
import com.bytmasoft.dss.dto.UserCreateDto;
import com.bytmasoft.dss.dto.UserDto;
import com.bytmasoft.dss.dto.UserUpdateDto;


public interface UserService extends ServiceApi<UserDto, UserCreateDto, UserUpdateDto> {

    UserDto addRoleToUser(Long userId, Long roleId);

    UserDto findByUsername(String username);

    Long countAllUsers();

    Long countAllActiveUsers();

    Long countAllLockedUsers();

    UserDto unlock(Long id);

    UserDto lockout(Long id);

    UserDto markfordeletion(Long id);
}
