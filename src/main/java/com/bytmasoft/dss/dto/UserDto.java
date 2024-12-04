package com.bytmasoft.dss.dto;


import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;



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
private Gender gender;
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
