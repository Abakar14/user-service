package com.bytmasoft.dss.dto;


import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {

private Gender gender;
private Long schoolId;
private String firstname;
private String lastname;
private String username;
private String email;
private String password;
private Role role;


}
