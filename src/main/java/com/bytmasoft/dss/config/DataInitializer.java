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
            Role role = null;
            if(roleService.count() == 0){
                RoleCreateDto roleCreateDto = RoleCreateDto.builder()
                                                      .name("SYSTEM_ADMIN")
                                                      .description("System Administrator")
                                                      .build();




              role = roleMapper.dtoToEntity(roleService.add(roleCreateDto));

            }

            if(userService.countAllUsers() == 0){
                userService.add(UserCreateDto.builder()
                        .firstname("Mahamat")
                        .lastname("Abakar")
                        .username("abakar")
                                        .schoolId(1L)
                                        .role(role)
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
}
