package com.bytmasoft.dss.dto;

import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class UserUpdateDto {

@Size(min = 3, max = 50)
private String firstname;

@NotNull
@Size(min = 3, max = 50)
private String lastname;


@Size(min = 3, max = 50)
private String username;


@Size(min = 6, max = 50)
private String password;

private String email;


private Gender gender;


private Long schoolId;

private Long roleId;

}
