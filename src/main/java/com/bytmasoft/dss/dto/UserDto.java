package com.bytmasoft.dss.dto;


import com.bytmasoft.common.enums.Gender;
import com.bytmasoft.dss.entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
private Long id;
private Long schoolId;
private String firstname;
private String lastname;
private String username;
private String email;
private String password;
private String gender;
private Role role;
private Boolean isActive;
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
private LocalDateTime addedOn;

@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
private LocalDateTime modifiedOn;
private String addedBy;
private String modifiedBy;
private boolean deleted;





}
