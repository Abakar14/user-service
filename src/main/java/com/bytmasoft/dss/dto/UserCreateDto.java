package com.bytmasoft.dss.dto;


import com.bytmasoft.common.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {

@NotNull
@Size(min = 3, max = 50)
private String firstname;

@NotNull
@Size(min = 3, max = 50)
private String lastname;

@NotNull
@Size(min = 3, max = 50)
private String username;

@NotNull
@Size(min = 6, max = 50)
private String password;

private String email;

@NotNull
private Gender gender;

@NotNull
private Long schoolId;

private Long roleId;

//private boolean deleted;
}
