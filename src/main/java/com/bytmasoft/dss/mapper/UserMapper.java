package com.bytmasoft.dss.mapper;


import com.bytmasoft.dss.dto.UserCreateDto;
import com.bytmasoft.dss.dto.UserDto;
import com.bytmasoft.dss.dto.UserUpdateDto;
import com.bytmasoft.dss.entities.User;
import org.mapstruct.*;

@Mapper (unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {


    User dtoToEntity(UserDto var1);

    User dtoCreateToEntity(UserCreateDto var1);


   // @Mapping(target = "roles", source = "roles", qualifiedByName = "roleSet")
    UserDto entityToDto(User var1);



    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    User partialUpdate(UserUpdateDto var1, @MappingTarget User var2);




}
