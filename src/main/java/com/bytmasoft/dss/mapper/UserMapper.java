package com.bytmasoft.dss.mapper;


import com.bytmasoft.dss.dto.UserCreateDto;
import com.bytmasoft.dss.dto.UserDto;
import com.bytmasoft.dss.dto.UserUpdateDto;
import com.bytmasoft.dss.entities.User;
import org.mapstruct.*;

import java.util.List;

@Mapper (componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(target = "role", source = "role")
    UserDto toUserDto(User user);

    @Mapping(target = "role", source = "role")
    User toUser(UserDto userDto);

    List<UserDto> toUserDtos(List<User> users);

    List<User> toUsers(List<UserDto> userDtos);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "role", ignore = true)
    User dtoCreateToEntity(UserCreateDto userCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "addedOn", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    @Mapping(target = "addedBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget User user);

}
