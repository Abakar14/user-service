package com.bytmasoft.dss.config;

import com.bytmasoft.dss.dto.RoleCreateDto;
import com.bytmasoft.dss.dto.RoleDto;
import com.bytmasoft.dss.dto.UserCreateDto;
import com.bytmasoft.dss.dto.UserDto;
import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.enums.Gender;
import com.bytmasoft.dss.mapper.RoleMapper;
import com.bytmasoft.dss.repository.RoleRepository;
import com.bytmasoft.dss.service.RoleServiceImpl;
import com.bytmasoft.dss.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {


    private final AppPropertiesConfig appPropertiesConfig;

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
private final RoleRepository roleRepository;
private final RoleMapper roleMapper;


@Override
    public void run(String... args) throws Exception {
        if(appPropertiesConfig.getInitData().isInitialize()){

            if(roleService.count() == 0){
                this.getRoles().forEach(roleCreateDto -> {
                    roleService.add(roleCreateDto);

                });


            }

            if(userService.countAllUsers() == 0){

               RoleDto roleDto = roleService.findRoleByName("ROLE_SYSTEM_ADMIN");

                userService.add(UserCreateDto.builder()
                        .firstname("Mahamat")
                        .lastname("Abakar")
                        .username("abakar")
                                        .schoolId(1L)
                                        .roleId(roleDto.getId())
                        .email("abakar@gmail.com")
                                .gender(Gender.MALE)
                        .password("Aba14mah?")
                        .build());



                List<UserDto> usersDto =  userService.findAllAsList();
                List<RoleDto> roles =  roleService.findAllAsList();
                //userService.addRoleToUser(usersDto.get(0).getId(), roles.get(0).getId());

                System.out.println("Initial data created successfully");

            }else {
                System.out.println("Data initialization flag is false, skipping initialization");
            }
        }
    }

    List<RoleCreateDto>getRoles(){
    List<RoleCreateDto> roles = new ArrayList<>();

        RoleCreateDto roleCreateDto = RoleCreateDto.builder()
                                              .name("SYSTEM_ADMIN")
                                              .description("Manage the entries platform(System settings, user accounts, role and permission)")
                                              .build();
        roles.add(roleCreateDto);


        RoleCreateDto roleCreateDto1 = RoleCreateDto.builder()
                                              .name("SCHOOL_ADMIN")
                                              .description("Overview day to day school operations.\n" +
                                                                   " Manage student and teacher data.\n" +
                                                                   "Handle fee and financial records.    \n")
                                              .build();
        roles.add(roleCreateDto1);

    return roles;
}

}
