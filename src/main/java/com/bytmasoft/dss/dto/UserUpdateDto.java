package com.bytmasoft.dss.dto;

import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class UserUpdateDto {

    private Long schoolId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
private Gender gender;

private Role role;


}
