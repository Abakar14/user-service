package com.bytmasoft.dss.config;

import com.bytmasoft.common.enums.Gender;
import com.bytmasoft.dss.dto.*;
import com.bytmasoft.dss.service.PermissionService;
import com.bytmasoft.dss.service.RoleService;
import com.bytmasoft.dss.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {


    private final AppPropertiesConfig appPropertiesConfig;

    private final UserService userService;
    private final RoleService roleService;
private final PermissionService permissionService;


@Override
    public void run(String... args) throws Exception {
        if(appPropertiesConfig.getInitData().isInitialize()){

            if(roleService.count() == 0){
                this.getRoles().forEach(roleCreateDto -> {
                    roleService.add(roleCreateDto);

                });

                if(permissionService.countPermissions() == 0){
                    this.getPermissions().forEach(permissionCreateDto -> {
                        permissionService.add(permissionCreateDto);

                    });
                }

                RoleDto roleDto = roleService.findRoleByName("ROLE_SYSTEM_ADMIN");

                List<PermissionDto> permissions = permissionService.findAllAsList();
                roleService.addPermissionsToRole(roleDto.getId(), permissions.stream().map(PermissionDto::getId).collect(Collectors.toList()));

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

                   System.out.println("Initial data created successfully");

            }else {
                System.out.println("Data initialization flag is false, skipping initialization");
            }
        }
    }

    List<PermissionCreateDto>getPermissions(){
        List<PermissionCreateDto> permissions = new ArrayList<>();
        PermissionCreateDto permissionCreateDto = PermissionCreateDto.builder()
                                                          .name("MANAGE_USERS")
                                                          .description("Create and assign roles to users")
                                                          .build();

        permissions.add(permissionCreateDto);

        PermissionCreateDto permissionCreateDto1 = PermissionCreateDto.builder()
                                                          .name("VIEW_USERS")
                                                          .description("Read all users")
                                                          .build();
        permissions.add(permissionCreateDto1);

        PermissionCreateDto permissionCreateDto2 = PermissionCreateDto.builder()
                                                           .name("MANAGE_STUDENTS")
                                                           .description("Create, update, delete students")
                                                           .build();
        permissions.add(permissionCreateDto2);

        PermissionCreateDto permissionCreateDto3 = PermissionCreateDto.builder()
                                                           .name("VIEW_STUDENTS")
                                                           .description("Show students")
                                                           .build();
        permissions.add(permissionCreateDto3);

        return permissions;

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
